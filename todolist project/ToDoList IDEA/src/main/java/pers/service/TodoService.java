package pers.service;

import org.springframework.stereotype.Service;
import pers.entity.ToDo;
import pers.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    @Resource
    public DefaultService<ToDo> toDoDefaultService;

    /**
     * 对待办事项信息进行处理
     */
    private List<Map<String, Object>> process(List<Map<String, Object>> toDos) {
        for (Map<String, Object> objectMap : toDos) {
            objectMap.put("startTimeStr", CommonUtils.formatDateTimeString((Date) objectMap.get("start_time")));
            objectMap.put("endTimeStr", CommonUtils.formatDateTimeString((Date) objectMap.get("end_time")));
            objectMap.remove("start_time");
            objectMap.remove("end_time");
        }
        return toDos;
    }


    /**
     * 限制的待办事项列表
     */
    public List<Map<String, Object>> limitToDo(int start, int end) {
        return process(toDoDefaultService.selectLimit(start, end));
    }

    /**
     * 全部待办事项列表
     */
    public List<Map<String, Object>> allToDo() {
        return process(toDoDefaultService.selectAll());
    }
}
