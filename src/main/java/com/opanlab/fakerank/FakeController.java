package com.opanlab.fakerank;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class FakeController {

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping("/rank")
    List<StudentScoreInfo> rank() {
        Faker faker = new Faker(new Locale("zh-CN"));
        List<StudentScoreInfo> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            StudentScoreInfo studentScoreInfo = new StudentScoreInfo();
            studentScoreInfo.setId(faker.number().numberBetween(2023000000L, 2023999999L));
            studentScoreInfo.setName(faker.name().name());
            List<LevelScore> scores = new ArrayList<>();
            for (int i1 = 0; i1 < 4; i1++) {
                LevelScore levelScore = new LevelScore();
                levelScore.setLevel(i1);
                if (faker.random().nextInt(0,1) == 0){
                    levelScore.setScore(10);
                }else{
                    levelScore.setScore(0);
                }
                scores.add(levelScore);
            }
            studentScoreInfo.setScore(scores);
            result.add(studentScoreInfo);
        }
        return result;
    }

}
