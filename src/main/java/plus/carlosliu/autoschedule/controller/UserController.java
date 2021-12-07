package plus.carlosliu.autoschedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plus.carlosliu.autoschedule.pojo.User;
import plus.carlosliu.autoschedule.service.UserService;
import plus.carlosliu.autoschedule.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/user/schedule/2021-12-20/2021-12-31/3
    @RequestMapping("/{startDate}/{endDate}/{count}")
    public BaseResponse scheduleDuty(@PathVariable String startDate,@PathVariable String endDate,@PathVariable Integer count){
        //查询所有人
        List<User> allUser = userService.list();

        if(allUser == null || allUser.size() == 0){
            return new BaseResponse(false ,"没有人可排班！");
        }
        List<String> dateList = new ArrayList<>();
        //只有开始时间，默认排完所有人，单人排
        if(startDate != null && endDate == null){
            dateList = DateUtil.getDateList(startDate, allUser.size()/count);
        //有开始与结束时间
        }else if(startDate != null && endDate != null){
            dateList = DateUtil.getDateList(startDate, endDate);
        }

        int totalSize = allUser.size();
        Map<String, List<User>> tempMap = new HashMap<>(dateList.size());
        for (int i = 0; i < dateList.size(); i++) {
            if (i < totalSize/count) {
                List<User> users = new ArrayList<>();
                for (int j = 0; j < count && !allUser.isEmpty(); j++) {
                    //随机下标
                    int index = (int) (Math.random() * allUser.size());
                    User user = allUser.get(index);
                    allUser.remove(index);
                    users.add(user);
                }

                String strDate = dateList.get(i);
                //排班完成
                tempMap.put(strDate, users);
            }else if (!allUser.isEmpty()){
                List<User> users = new ArrayList<>();
                for (int j = 0; j < count && !allUser.isEmpty(); j++) {
                    int index = (int) (Math.random() * allUser.size());
                    User user = allUser.get(index);
                    allUser.remove(index);
                    users.add(user);
                }
                String strDate = dateList.get(i);
                tempMap.put(strDate, users);
            }else {
                String proDate = dateList.get(i - totalSize / count - 1);
                List<User> users = tempMap.get(proDate);
                String strDate = dateList.get(i);
                tempMap.put(strDate, users);
            }
        }
        return new BaseResponse(true, tempMap);
    }
}
