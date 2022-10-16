package com.navigate.ship.ship.provider;

import com.itprom.ship.common.bean.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "application")
public class BoardProvider {
    private  final List<Board> boards = new ArrayList<>();
}
