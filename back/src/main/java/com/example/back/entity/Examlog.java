package com.example.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 *
 * @TableName examlog
 */
@TableName(value ="examlog")
@Data
public class Examlog implements Serializable {
    /**
     *
     */
    @TableId(value = "LogID", type = IdType.AUTO)
    private Integer logid;

    /**
     *
     */
    @TableField(value = "OperationType")
    private String operationtype;

    /**
     *
     */
    @TableField(value = "InfoID")
    private Integer infoid;

    /**
     *
     */
    @TableField(value = "TeacherID")
    private String teacherid;

    /**
     *
     */
    @TableField(value = "OperationTime")
    private Date operationtime;

    /**
     *
     */
    @TableField(value = "Operator")
    private String operator;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Examlog other = (Examlog) that;
        return (this.getLogid() == null ? other.getLogid() == null : this.getLogid().equals(other.getLogid()))
                && (this.getOperationtype() == null ? other.getOperationtype() == null : this.getOperationtype().equals(other.getOperationtype()))
                && (this.getInfoid() == null ? other.getInfoid() == null : this.getInfoid().equals(other.getInfoid()))
                && (this.getTeacherid() == null ? other.getTeacherid() == null : this.getTeacherid().equals(other.getTeacherid()))
                && (this.getOperationtime() == null ? other.getOperationtime() == null : this.getOperationtime().equals(other.getOperationtime()))
                && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogid() == null) ? 0 : getLogid().hashCode());
        result = prime * result + ((getOperationtype() == null) ? 0 : getOperationtype().hashCode());
        result = prime * result + ((getInfoid() == null) ? 0 : getInfoid().hashCode());
        result = prime * result + ((getTeacherid() == null) ? 0 : getTeacherid().hashCode());
        result = prime * result + ((getOperationtime() == null) ? 0 : getOperationtime().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logid=").append(logid);
        sb.append(", operationtype=").append(operationtype);
        sb.append(", infoid=").append(infoid);
        sb.append(", teacherid=").append(teacherid);
        sb.append(", operationtime=").append(operationtime);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}