/**
 * 
 */
package com.namNguyen03.Chat.Backend.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.namNguyen03.Chat.Backend.seedwork.MyAuditable;
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
public class RoomChat implements MyEntity, MyAuditable{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private UUID uuid;
    @Column(columnDefinition="varchar(64)")
    private String name;
    @Column(columnDefinition="varchar(128)")
    private String description; 
    private boolean isPublic = false;
    @Column(columnDefinition="varchar(64)")
    private String createBy;
    @Column(columnDefinition="varchar(64)")
    private String updateBy;
    private LocalDate createDate;
    private LocalDate updateDate;
    
}
