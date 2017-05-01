package com.kris.kuaisuyuedu.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Contants {
	public static String[] WEATHER_PIC_NAME;
	public static Map<String,String> nMap = new HashMap<String,String>();
	
	public final static String VERKEY = "IEALSGJBEWI654654";
	
	public static String getToken() {
		
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
	
	/** 手动推送类型 */
	public enum NotifyType {
		
		/**通知抢单人付款 */
		ROBPAY(2, "通知抢单人付款"),
		/**通知发单人显示抢单人正在付款消息 */
		RELEASEWAIT(3, "通知发单人显示抢单人正在付款消息");

		private final Integer value;
		private final String label;

		private NotifyType(Integer value, String label) {
			this.value = value;
			this.label = label;
		}

		public Integer getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

		public static NotifyType valueOf(Integer value) {
			for (NotifyType inst : values()) {
				if (value == inst.value)
					return inst;
			}
			throw new IllegalArgumentException("不支持的常量：" + value);
		}

		public static Map<Integer, String> getMap() {
			Map<Integer, String> map = new HashMap<Integer, String>();

			for (NotifyType inst : values()) {

				map.put(inst.value, inst.label);
			}

			return map;
		}
	}
	
	/** 结算类型 */
	public enum SettlementType {
		/** 保证金 = 结算金额 */
		MARGINEQUAL(1, "保证金 = 结算金额"),
		/** 保证金 < 结算金额*/
		MARGINLESSTHAN(2, "保证金 < 结算金额"),
		/** 保证金 > 结算金额*/
		MARGINGREATERTHAN(3, "保证金 > 结算金额");

		private final Integer value;
		private final String label;

		private SettlementType(Integer value, String label) {
			this.value = value;
			this.label = label;
		}

		public Integer getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

		public static SettlementType valueOf(Integer value) {
			for (SettlementType inst : values()) {
				if (value == inst.value)
					return inst;
			}
			throw new IllegalArgumentException("不支持的常量：" + value);
		}

		public static Map<Integer, String> getMap() {
			Map<Integer, String> map = new HashMap<Integer, String>();

			for (SettlementType inst : values()) {

				map.put(inst.value, inst.label);
			}

			return map;
		}
	}
	
	
	
	
	
	
	/** 支付方式 */
	public enum PayType {
		WECHATPAY("1", "微信支付"),
		ALIPAY("2", "支付宝支付"),
		OTHER("3", "其他");
		
        private final String value;
        private final String label;

        private PayType(String value, String label){
            this.value = value;
            this.label = label;
        }

        public String getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static Map<String, String> getMap(){
			Map<String, String> map = new HashMap<String, String>();
			
			for(PayType inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 拼车订单表（POR）状态 */
	public enum OrderStatus {
		
		DISABLE(0, "禁用"),
		ORDERS(1, "已下单"),
		FIGHTORDERS(2, "已拼单"),
		RECEIVEDDEPOSIT(3, "已收保证金"),
		ALREADYCALLEDTHECAR(4, "已叫车"),
		SETTLEMENTING(5, "等待结算"),
		SETTLEMENTED(6, "已结算"),
		CANCEL(7, "已取消"),
		DELETE(8, "已作废"),
		DEPOSITFAIL(9, "保证金失败"),
		FIGHTING(10, "用户拼车中"),
		FIGHTED(11, "用户已拼车");
		
        private final Integer value;
        private final String label;

        private OrderStatus(Integer value, String label){
            this.value = value;
            this.label = label;
        }

        public Integer getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static OrderStatus valueOf(Integer value){
            for(OrderStatus inst : values()){
                if(value == inst.value)
                    return inst;
            }
            throw new IllegalArgumentException("不支持的常量：" + value);
        }
        
        public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(OrderStatus inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 拼车对象表（POT）状态 */
	public enum RobOrderStatus {
		
		DISABLE(0, "禁用"),
		ORDERS(1, "已下单"),
		RECEIVEDDEPOSIT(2, "已付保证金"),
		DEPOSITFAIL(3, "保证金失败"),
		ROBORDERFAIL(4, "抢单失败"),
		SETTLEMENTED(5, "已结算"),
		SETTLEMENTFAIL(6, "结算失败");
		
        private final Integer value;
        private final String label;

        private RobOrderStatus(Integer value, String label){
            this.value = value;
            this.label = label;
        }

        public Integer getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static RobOrderStatus valueOf(Integer value){
            for(RobOrderStatus inst : values()){
                if(value == inst.value)
                    return inst;
            }
            throw new IllegalArgumentException("不支持的常量：" + value);
        }
        
        public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(RobOrderStatus inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 拼车用户表（PUR）订单状态 */
	public enum UserOrderStatus {
		
		CANORDER(0, "需发单"),
		CANROBORDER(1, "可抢单"),
		UNFINISHEDORDER(2, "有未完单"),
		BREAKPROMISEORDER(3, "有违约");
		
        private final Integer value;
        private final String label;

        private UserOrderStatus(Integer value, String label){
            this.value = value;
            this.label = label;
        }

        public Integer getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static UserOrderStatus valueOf(Integer value){
            for(UserOrderStatus inst : values()){
                if(value == inst.value)
                    return inst;
            }
            throw new IllegalArgumentException("不支持的常量：" + value);
        }
        
        public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(UserOrderStatus inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 出发时间类型 */
	public enum DepartureTimeType {
		NOW(0, "马上出发"),
		LATER(1, "具体时间");
		
		private final Integer value;
		private final String label;
		
		private DepartureTimeType(Integer value, String label){
			this.value = value;
			this.label = label;
		}
		
		public Integer getValue(){
			return value;
		}
		
		public String getLabel() {
			return label;
		}
		
		public static DepartureTimeType valueOf(Integer value){
			for(DepartureTimeType inst : values()){
				if(value == inst.value)
					return inst;
			}
			throw new IllegalArgumentException("不支持的常量：" + value);
		}
		
		public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(DepartureTimeType inst : values()){ 
				
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	/** 银行卡类型 */
	public enum BankCardType {
		JIEJI(1, "储蓄卡"),
		DAIJI(2, "信用卡"),
		ZDAIJI(3, "准贷记卡"),
		YUFUFEI(4, "预付费卡");
		
        private final Integer value;
        private final String label;

        private BankCardType(Integer value, String label){
            this.value = value;
            this.label = label;
        }

        public Integer getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static BankCardType valueOf(Integer value){
            for(BankCardType inst : values()){
                if(value == inst.value)
                    return inst;
            }
            throw new IllegalArgumentException("不支持的常量：" + value);
        }
        
        public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(BankCardType inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 状态 */
	public enum Status {
		DISABLE(0, "禁用"),
		NORMAL(1, "正常");
		
		private final Integer value;
		private final String label;
		
		private Status(Integer value, String label){
			this.value = value;
			this.label = label;
		}
		
		public Integer getValue(){
			return value;
		}
		
		public String getLabel() {
			return label;
		}
		
		public static Status valueOf(Integer value){
			for(Status inst : values()){
				if(value == inst.value)
					return inst;
			}
			throw new IllegalArgumentException("不支持的常量：" + value);
		}
		
		public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(Status inst : values()){ 
				
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	/** 图片保存路径  */
	public enum ImagePath {
		HEADIMG("/uploads/headImg/", "用户头像");
		
		private final String value;
		private final String label;
		
		private ImagePath(String value, String label){
			this.value = value;
			this.label = label;
		}
		
		public String getValue(){
			return value;
		}
		
		public String getLabel() {
			return label;
		}
		
		public static Map<String, String> getMap(){
			Map<String, String> map = new HashMap<String, String>();
			
			for(ImagePath inst : values()){ 
				
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 登录系统 */
	public enum SystemType {
		WEB("WEB", 0),
		IOS("IOS", 1),
		ANDROID("ANDROID", 2);
		
        private final String value;
        private final Integer label;

        private SystemType(String value, Integer label){
            this.value = value;
            this.label = label;
        }

        public String getValue(){
            return value;
        }

        public Integer getLabel() {
            return label;
        }

        public static Map<String, Integer> getMap(){
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			for(SystemType inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	
	/** 文件保存路径  */
	public enum FileUploadPath {
		UPLOADLOCALPATH("/uploads/ftpFile/", "文件本地保存路径"),
		UPLOADFTPPATH("/", "文件FTP保存路径");
		
		private final String value;
		private final String label;
		
		private FileUploadPath(String value, String label){
			this.value = value;
			this.label = label;
		}
		
		public String getValue(){
			return value;
		}
		
		public String getLabel() {
			return label;
		}
		
		public static Map<String, String> getMap(){
			Map<String, String> map = new HashMap<String, String>();
			
			for(FileUploadPath inst : values()){ 
				
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	/** 文件上传状态 */
	public enum FileUploadStatus {
		
		DISABLE(0, "禁用"),
		UNUPLOAD(1, "未上传"),
		UPLOADED(2, "已上传"),
		UPLOADFAIL(3, "上传失败");
		
        private final Integer value;
        private final String label;

        private FileUploadStatus(Integer value, String label){
            this.value = value;
            this.label = label;
        }

        public Integer getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static FileUploadStatus valueOf(Integer value){
            for(FileUploadStatus inst : values()){
                if(value == inst.value)
                    return inst;
            }
            throw new IllegalArgumentException("不支持的常量：" + value);
        }
        
        public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(FileUploadStatus inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	/** 连接端口 */
	public enum ConnectPort {
		
		FTP(21, "ftp连接端口");
		
        private final Integer value;
        private final String label;

        private ConnectPort(Integer value, String label){
            this.value = value;
            this.label = label;
        }

        public Integer getValue(){
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static ConnectPort valueOf(Integer value){
            for(ConnectPort inst : values()){
                if(value == inst.value)
                    return inst;
            }
            throw new IllegalArgumentException("不支持的常量：" + value);
        }
        
        public static Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			for(ConnectPort inst : values()){ 
			
				map.put(inst.value, inst.label);
			}
			
			return map;
		}
	}
	
	
	
}
