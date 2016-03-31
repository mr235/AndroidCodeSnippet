package com.mr235.androidcodesnippet.util;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventHandler {

	private static Map<Event, Method> sMethods;

	static {
		sMethods = new HashMap<Event, Method>();
		for (Method m : EventHandler.class.getMethods()) {
			String name = m.getName();
			try {
				Event evt = Event.valueOf(name);
				sMethods.put(evt, m);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	private static Map<Event, HashSet<EventHandler>> handlers = new HashMap<Event, HashSet<EventHandler>>();

	public static void addEventHandler(Event[] evts, EventHandler handler) {
		synchronized (handlers) {
			if (null == handler || evts == null)
				return;
			for (Event evt : evts) {
				HashSet<EventHandler> set = handlers.get(evt);
				if (set == null) {
					set = new HashSet<EventHandler>();
					handlers.put(evt, set);
				}
				set.add(handler);
			}

		}
	}

	public static void removeEventHandler(Event[] evts, EventHandler handler) {
		synchronized (handlers) {
			if (evts == null || handler == null)
				return;

			for (Event evt : evts) {
				Set<EventHandler> set = handlers.get(evt);
				if (set != null) {
					set.remove(handler);
				}
			}

		}
	}

	public static void notifyEvent(Event evt, Object... paras) {
		synchronized (handlers) {

			Set<EventHandler> set = handlers.get(evt);
			if (set == null)
				return;
			for (EventHandler handler : set) {
				EventHandler.doNotify(evt, handler, paras);
			}
		}
	}

	private static void doNotify(final Event evt, final EventHandler handler,
	                             final Object... args) {
		synchronized (sMethods) {
			final Method method = sMethods.get(evt);
			if (method == null) {
				return;
			}
			// do all callback in main thread
			Runnable task = new Runnable() {

				@Override
				public void run() {
					try {
						long t1 = System.currentTimeMillis();
						method.invoke(handler, new Object[]{args});
						long t2 = System.currentTimeMillis();
					} catch (Throwable t) {
						// zzw: throw it anyway
						// if (Utils.isDebugMode()) {
						throw new RuntimeException(t);
						// }
					}
				}
			};
			long threadId = Thread.currentThread().getId();
			long mainId = Looper.getMainLooper().getThread().getId();
			if (threadId == mainId) {
				task.run();
			} else {
				new Handler(Looper.getMainLooper()).post(task);
			}
		}
	}

	public static enum Event {
		updateUserInfo,
	}

	/**
	 * 用户信息更新
	 */
	public void updateUserInfo(Object... args) {
	}
}