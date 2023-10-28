package com.opanlab.fakerank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StudentScoreInfo {
    String name;
    Long id;
    List<LevelScore> score;

}
