package com.mr235.androidcodesnippet.db.table;

/**
 * Created by Administrator on 2015/4/22.
 * 小组信息表
 */
public interface GroupInfoTable {

    /** 表名 */
    String TABLE_NAME = "group_info";
    /**
     * 小组地址
     */
    String ADDRESS="address";
    /**
     * 所属区域
     */
    String AREA="area";
    /**
     * 小组包含的菜品大类别
     */
    String CATEX="catex";
    /**
     * 开组日期
     */
    String CDATE="cdate";
    /**
     * 是否填楼层信息,y填n不填
     */
    String CENG="ceng";
    /**
     * 所属城市
     */
    String CITY="city";
    /**
     * 楼层二级信息 XX单元
     */
    String DANYUAN="danyuan";
    /**
     * 大厦名称
     */
    String DASHA_NAME="dasha_name";
    /**
     * 满多少送给双倍积分，默认100
     */
    String DOUBLE_JIFEN="double_jifen";
    /**
     * 期望销售额
     */
    String EXPECT="expect";
    /**
     * 楼层一级信息后缀 XX楼 XX座
     */
    String F1 = "f1";
    /**
     * 楼层二级信息后缀 XX单元
     */
    String F2 = "f2";
    /**
     * 楼层三级信息 XX层
     */
    String FLOOR="floor";
    /**
     * 前台显示隐藏真实的订餐人数
     */
    String HIDENUM="hidenum";
    /**
     * 楼宇类型， 0写字楼 1医院 2学校 3商场 4住宅
     */
    String HOST_TYPE="host_type";
    /**
     * 小组id
     */
    String ID="id";
    /**
     * 积分的奖品
     */
    String JIFEN_TEXT="jifen_text";
    /**
     * 楼层一级信息 XX楼 XX座
     */
    String JIHAO="jihao";
    /**
     * 搜索关键字、别名
     */
    String KEYWORD="keyword";
    /**
     * 楼宇在地球的纬度
     */
    String LAT="lat";
    /**
     * 楼宇在地球的经度
     */
    String LNG="lng";
    /**
     * 是否填房间号，y填n不填
     */
    String ROOM="room";
    /**
     * 负责该小组的业务员id
     */
    String SALER_UID="saler_uid";
    /**
     * 小组信息，y正常；n不正常；stop停止；unready未准备好；ready准备好了；pass通过审核；unpass未通过审核
     */
    String STATUS="status";
    /**
     * 小组域名前缀
     */
    String SUB="sub";
    /**
     * 是否支持团餐 0不支持 1支持
     */
    String TUAN="tuan";
    /**
     * 小组名成
     */
    String ZH_NAME="zh_name";
}
