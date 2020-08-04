package cn.yer.activiti.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author hzc
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_USER_YHXX")
public class UserYhxx extends Model<UserYhxx> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;

    /**
     * 姓名
     */
    @TableField("XM")
    private String xm;

    /**
     * 联系电话
     */
    @TableField("LXDH")
    private String lxdh;

    /**
     * 所属单位编码
     */
    @TableField("SSJGBH")
    private String ssjgbh;

    /**
     * 所属单位名称
     */
    @TableField("SSJGMC")
    private String ssjgmc;

    /**
     * 上级单位编码
     */
    @TableField("SJJGBH")
    private String sjjgbh;

    /**
     * 上级单位名称
     */
    @TableField("SJJGMC")
    private String sjjgmc;

    /**
     * 注册时间
     */
    @TableField("ZCSJ")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受从前端传入的日期格式，映射到java类日期属性的规则
    private LocalDateTime zcsj;

    /**
     * 注销时间
     */
    @TableField("ZXSJ")
    private LocalDateTime zxsj;

    /**
     * 注销标识   0正常  1注销
     */
    @TableField("ZXBS")
    private String zxbs;

    /**
     * 关联SYS_USER表主键
     */
    @TableField("GL_ID")
    private String glId;

    /**
     * 0待审核     1正常
     */
    @TableField("YHZT")
    private String yhzt;

    /**
     * 微信openid
     */
    @TableField("OPENID")
    private String openid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
