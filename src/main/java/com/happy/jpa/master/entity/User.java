package com.happy.jpa.master.entity;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jun.wang
 * @title: User
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/2814:34
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    private final static String key = "7iEoPKNPw0DGUjTg";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email")
    @ColumnTransformer(
            read = "AES_DECRYPT(from_base64(email),'" + key + "')",
            write = "to_base64(AES_ENCRYPT(?, '7iEoPKNPw0DGUjTg'))"
    )
    private String email;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
