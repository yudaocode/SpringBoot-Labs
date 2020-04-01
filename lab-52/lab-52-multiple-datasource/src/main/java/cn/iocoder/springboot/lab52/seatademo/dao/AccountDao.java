package cn.iocoder.springboot.lab52.seatademo.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao {

    /**
     * 获取账户余额
     *
     * @param userId 用户 ID
     * @return 账户余额
     */
    @Select("SELECT balance FROM account WHERE id = #{userId}")
    Integer getBalance(@Param("userId") Long userId);

    /**
     * 扣减余额
     *
     * @param price 需要扣减的数目
     * @return 影响记录行数
     */
    @Update("UPDATE account SET balance = balance - #{price} WHERE id = 1 AND balance >= ${price}")
    int reduceBalance(@Param("price") Integer price);

}
