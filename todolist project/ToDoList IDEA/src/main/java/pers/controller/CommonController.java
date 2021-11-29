package pers.controller;

import org.springframework.web.bind.annotation.*;
import pers.dao.DefaultDao;
import pers.entity.ToDo;
import pers.service.DefaultService;
import pers.service.TodoService;
import pers.utils.CommonUtils;
import pers.utils.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// This is a todolist project with Spring boot and Vue3
@RestController
public class CommonController {

    @Resource
    private TodoService todoService;

    @Resource
    private DefaultService<ToDo> toDoDefaultService;

    /**
     * @return 全部待办事项
     */
    @GetMapping("/allToDos")
    public List<Map<String, Object>> allToDos() {
        return todoService.allToDo();
    }

    /**
     * @return 限制的待办事项
     */
    @GetMapping("/selectToDoList/{start}/{end}")
    public List<Map<String, Object>> selectToDoList(@PathVariable int start, @PathVariable int end) {
        return todoService.limitToDo(start, end);
    }

    /**
     * @return 待办事项总数
     */
    @GetMapping("/selectToDoTotal")
    public int selectToDoTotal() {
        return toDoDefaultService.selectLength();
    }

    /**
     * 更新一个待办事项的信息
     */
    @PostMapping("/updateToDo")
    public boolean updateToDo(@RequestParam int id,
                              @RequestParam String title,
                              @RequestParam String details,
                              @RequestParam String endTimeStr) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("details", details);
        map.put("end_time", endTimeStr);
        toDoDefaultService.update(map);
        return true;
    }

    /**
     * 添加一条待办事项
     */
    @PostMapping("/addToDo")
    public boolean insertToDo(@RequestParam String title,
                              @RequestParam String details,
                              @RequestParam String endTimeStr) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("details", details);
        map.put("start_time", CommonUtils.getDateTimeNow());
        map.put("end_time", endTimeStr);
        toDoDefaultService.insert(map);
        return true;
    }

    /**
     * 删除一条待办事项
     */
    @PostMapping("/deleteToDo")
    public boolean deleteToDo(@RequestParam int id) {
        toDoDefaultService.deleteById(id);
        return true;
    }
}
