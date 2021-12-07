package plus.carlosliu.autoschedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.carlosliu.autoschedule.mapper.UserMapper;
import plus.carlosliu.autoschedule.pojo.User;
import plus.carlosliu.autoschedule.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
