package com.namNguyen03.Chat.Backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.namNguyen03.Chat.Backend.model.RoomChat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nam
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomChatRepoIntegrationTest 
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoomChatRepo roomChatRepo;

    private  Pageable pageable = PageRequest.of(1, 5);

    @Test 
    public void whenNameExistsIsUsedToSearchByName_thenReturnPageRoomChat(){
        RoomChat roomChat;
        String name = "n_a_m*n_g_u_y_e_n";
        int i;
        for(i = 0 ; i<5 ; i++){
            roomChat = new RoomChat();
            roomChat.setName(name + i);
            entityManager.persist(roomChat);
		    entityManager.flush();
        }
        roomChat = new RoomChat();
        roomChat.setName("toan");
        entityManager.persist(roomChat);
		entityManager.flush();
        for(; i<10 ; i++ ){
            roomChat = new RoomChat();
            roomChat.setName(name + i);
            entityManager.persist(roomChat);
		    entityManager.flush();
        }
      
        Page<RoomChat> rs = roomChatRepo.searchByName('%' + name + '%', pageable);
        assertEquals( 5, rs.getContent().size());
        assertEquals(name + 5 , rs.getContent().get(0).getName());
    }

    @Test
    public void whenCreateByExistsIsUsedToSearchByCreateBy_thenReturnPageRoomChat(){
        RoomChat roomChat;
        String createBy = "A_d_M_i_N*a_P_p*C_h_A_t";
        int i;
        for(i = 0 ; i<5 ; i++){
            roomChat = new RoomChat();
            roomChat.setCreateBy(createBy +i);
            entityManager.persist(roomChat);
		    entityManager.flush();
        }
        roomChat = new RoomChat();
        roomChat.setCreateBy("user");
        entityManager.persist(roomChat);
		entityManager.flush();
        for(; i<10 ; i++ ){
            roomChat = new RoomChat();
            roomChat.setCreateBy(createBy +i);
            entityManager.persist(roomChat);
		    entityManager.flush();
        }
      
        Page<RoomChat> rs = roomChatRepo.searchByCreateBy('%' + createBy + '%', pageable);
        assertEquals( 5, rs.getContent().size());
        assertEquals(createBy + 5 , rs.getContent().get(0).getCreateBy());
    }

}

