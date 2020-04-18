package cn.iocoder.springboot.lab55.mapstructdemo;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.UserDetailBO;
import cn.iocoder.springboot.lab55.mapstructdemo.convert.UserConvert;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.UserDO;

public class UserDetailBOTest {

    public static void main(String[] args) {
        // 创建 UserDO 对象
        UserDO userDO = new UserDO()
                .setId(1).setUsername("yudaoyuanma").setPassword("buzhidao");
        // 进行转换
        UserDetailBO userDetailBO = UserConvert.INSTANCE.convertDetail(userDO);
        System.out.println(userDetailBO.getUserId());
    }

}
