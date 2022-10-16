package com.nagigate.ship.common.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Port {
    private String name;
    private List<String> boards = new ArrayList<>();
    private int x;
    private  int y;

    public void addBoard(String boardName){
        int ind = boards.indexOf(boardName);
        if (ind>=0){
            boards.set(ind,boardName);

        }else{
            boards.add(boardName);
        }
    }

   public   void  removeBoard(String boardName){
        boards.remove(boardName);

    }
}
