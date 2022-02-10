/**
 * 
 */
package com.namNguyen03.Chat.Backend.model;

import java.util.UUID;

import javax.persistence.Entity;
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
    private int id;
    private UUID uuid;
    private String username;
    private String password;
    private String fullName;

}
