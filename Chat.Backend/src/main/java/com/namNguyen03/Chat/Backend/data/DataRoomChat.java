package com.namNguyen03.Chat.Backend.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.namNguyen03.Chat.Backend.model.RoomChat;
import com.namNguyen03.Chat.Backend.repository.RoomChatRepo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration

public class DataRoomChat {
    
    @Value("${fake.data.environment}")
    private String environment;

    @Autowired
    private RoomChatRepo roomChatRepo;

    @Bean
    public void addDataRoomChat(){
        String fileName ="";

        if("test".equals(environment)){
            fileName = "room_chat_test.json";
        }

        if("dev".equals(environment)){
            fileName = "room_chat_dev.json";
        }

        try {
            File file = ResourceUtils.getFile("classpath:static/data/" + fileName);
            FileReader fileStream = new FileReader(file);
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(fileStream);
            if(!arr.isEmpty()){
            
                Object obj = arr.get(0);
                JSONObject roomChat = (JSONObject) obj;
            
                UUID uuid = UUID.fromString(roomChat.get("uuid").toString());

                if(roomChatRepo.existsByUuid(uuid)){
                    return;
                }
                String name;
                String description;
                String createBy;
                LocalDate createDate;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                RoomChat room;
                for(Object o : arr){
                    roomChat = (JSONObject) o;
                    name = roomChat.get("name").toString();
                    description = roomChat.get("description").toString();
                    createBy = roomChat.get("create_by").toString();
                    createDate = LocalDate.parse(roomChat.get("create_date").toString(), formatter);
                    uuid = UUID.fromString(roomChat.get("uuid").toString());

                    room = new RoomChat();
                    room.setCreateBy(createBy);
                    room.setName(name);
                    room.setDescription(description);
                    room.setCreateDate(createDate);
                    room.setUuid(uuid);
                    roomChatRepo.save(room);
                }
            }
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        }

    }
}
