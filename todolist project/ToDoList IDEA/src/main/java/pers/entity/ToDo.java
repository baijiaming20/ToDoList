package pers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    private int id;
    private String title, details, startTimeStr, endTimeStr;
    private Date start_time, end_time;
}
