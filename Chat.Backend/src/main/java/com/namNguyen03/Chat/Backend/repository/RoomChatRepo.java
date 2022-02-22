/**
 * 
 */
package com.namNguyen03.Chat.Backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.namNguyen03.Chat.Backend.model.RoomChat;

/**
 * @author nam
 *
 */
@Repository
public interface RoomChatRepo extends PagingAndSortingRepository<RoomChat, Integer> {

    /**
     * Search By name and get page   
     * 
     * @param valueSearch must not be {@literal null}.
     * @param pageable must not be {@literal null}.
     * @return Page<RoomChat>
     */
    @Query(
      value = "SELECT * FROM room_chat where name like :name", 
      countQuery = "SELECT count(*) FROM room_chat where name like :name", 
      nativeQuery = true)
    Page<RoomChat> searchByName(@Param("name") String name, Pageable pageable);

      /**
     * Search By create by and get page   
     * 
     * @param valueSearch must not be {@literal null}.
     * @param pageable must not be {@literal null}.
     * @return Page<RoomChat>
     */
    @Query(
      value = "SELECT * FROM room_chat where create_by like :createBy", 
      countQuery = "SELECT count(*) FROM room_chat where name like :createBy", 
      nativeQuery = true)
    Page<RoomChat> searchByCreateBy(@Param("createBy") String createBy, Pageable pageable);


}
