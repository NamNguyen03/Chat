/**
 * 
 */
package com.namNguyen03.Chat.Backend.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.namNguyen03.Chat.Backend.seedwork.MyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User implements MyEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private UUID uuid;
    @Column(columnDefinition="varchar(64)")
    private String username;
    @Column(columnDefinition="varchar(64)")
    private String password;
    @Column(columnDefinition="nvarchar(64)")
    private String fullName;

}
