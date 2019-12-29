package com.nuo.ydta.service.impl;

import com.google.common.collect.Lists;
import com.nuo.ydta.domain.PushBean;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.dto.RoleNpcDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.FavorabilityRepository;
import com.nuo.ydta.service.FavorabilityService;
import com.nuo.ydta.service.JiGuangPushService;
import com.nuo.ydta.service.PushService;
import com.nuo.ydta.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorabilityServiceImpl implements FavorabilityService {

    @Autowired
    private FavorabilityRepository favorabilityRepository;

    @Autowired
    private PushService pushService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<RoleNpc> getFavorabilitys() {
        try {
            List<RoleNpc> list = favorabilityRepository.findAll();
            return list;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void updategetFavorabilitys(String roleName, String npcName, int favorability) {
        try {
            RoleNpc rn = favorabilityRepository.findByRoleNameAndNpcName(roleName, npcName);
            int f = rn.getFavorability() + favorability;
            if (f < 0) {
                rn.setFavorability(0);
            } else if (f > 100) {
                rn.setFavorability(100);
            } else {
                rn.setFavorability(f);
            }
            favorabilityRepository.save(rn);
            Role role = roleService.getRoleByName(roleName);
            if (favorability >= 0) {
                pushService.push("壹點探案---好感度", "恭喜您与" + npcName + "的好感度增加了" + favorability, role.getId(), npcName);
            } else {
                pushService.push("壹點探案---好感度", "您与" + npcName + "的好感度减少了" + Math.abs(favorability), role.getId(), npcName);
            }

            int count = 0;
            List<RoleNpc> roleNpcs = favorabilityRepository.findByRoleName(roleName);
            if (f >= 90) {
                pushService.push("壹點探案---好感度", "恭喜与" + npcName + "好感度已达90，可前往夏晗居接取义结金兰或百年好合任务", role.getId(), npcName);
            }

            if (roleName.equals("灵心") && npcName.equals("周疆主") && f == 100) {
                pushService.push("壹點探案---好感度", "可前往找到周疆主，抉择是否要与他进行再续前缘任务", role.getId(), npcName);
            }

            if (npcName.equals("乔乔")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "警告与乔乔好感度已低至10，若与乔乔好感度继续下降，在百荟铺购买任意商品将支付两倍价格！", role.getId(), npcName);
                } else if (f >= 80) {
                    pushService.push("壹點探案---好感度", "恭喜与乔乔好感度已达80，可获得在百荟铺购买商品享受优惠的特权！", role.getId(), npcName);
                }

            }

            if (npcName.equals("薛瑞")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "警告与薛瑞好感度已低至10，若与薛瑞好感度持续下降，在凝宝斋的任意交易将付出双倍代价！", role.getId(), npcName);
                }
            }

            if (npcName.equals("寒星")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "恭喜与寒星好感度已低至10，若与寒星好感度继续下降，在星焰将会受到意想不到的代价或惩罚！", role.getId(), npcName);
                }
            }

            if (npcName.equals("倏")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "警告与倏好感度已低至10，若与倏好感度继续下降，将失去在益寿堂降低嫌疑值的机会！", role.getId(), npcName);
                }
            }

            if (npcName.equals("姜良")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "警告与姜良好感度已低至10，若与姜良好感度继续下降，将在姜氏铁铺面临意想不到的代价或惩罚！", role.getId(), npcName);
                }
            }


            if (npcName.equals("玉儿")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "警告与玉儿好感度已低至10，若与玉儿好感度继续下降，将在醉尘阁面临意想不到的代价或惩罚！", role.getId(), npcName);
                }
            }


            if (npcName.equals("天华")) {
                if (f <= 10) {
                    pushService.push("壹點探案---好感度", "警告与天华好感度已低至10，若与天华好感度继续下降，将在乐坊面临意想不到的代价或惩罚！", role.getId(), npcName);
                }
            }
            if (f <= 10) {
                pushService.push("壹點探案---好感度", "与周疆主好感度已低至10，若与周疆主好感度继续下降，将面临杖刑责罚！", role.getId(), npcName);
            }

            for (RoleNpc roleNpc : roleNpcs) {
                RoleNpcDto dto = new RoleNpcDto();
                BeanUtils.copyProperties(roleNpc, dto);

                if (dto.getNpcName().equals("周疆主") && dto.getFavorability() >= 60) {
                    count++;
                }

                if (!dto.getNpcName().equals("周疆主") && dto.getFavorability() >= 80) {
                    count++;
                }
            }

            if (count == 8) {
                PushBean pushBean = new PushBean();
                pushBean.setAlert("恭喜解锁隐藏成就‘众星捧月’，可找到倏，了解被人拥戴之后的特权！");
                pushService.push("壹點探案---好感度", pushBean.getAlert(), rn.getRoleId(), "系统");
            }


        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Page<RoleNpc> pageQuery(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return favorabilityRepository.findAll(pageable);
    }

    @Override
    public List<RoleNpcDto> findByRoleName(String roleName) {
        List<RoleNpcDto> dtoList = Lists.newArrayList();
        List<RoleNpc> roleNpcs = favorabilityRepository.findByRoleName(roleName);
        for (RoleNpc roleNpc : roleNpcs) {
            RoleNpcDto dto = new RoleNpcDto();
            BeanUtils.copyProperties(roleNpc, dto);
            if (!dto.getNpcName().equals("周疆主")) {
                if (dto.getFavorability() >= 90) {
                    switch (dto.getNpcName()) {
                        case "乔乔":
                        case "姜良":
                        case "寒星":
                        case "薛瑞":
                        case "倏":
                        case "玉儿":
                        case "天华":
                            dto.setDesc("恭喜与" + dto.getNpcName() + "好感度已达90，可前往夏晗居接取义结金兰或百年好合任务");
                            break;
                        default:
                            break;
                    }
                }
            } else {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与周疆主好感度已低至10，若与周疆主好感度继续下降，将面临杖刑责罚！");
                }
            }

            if (roleName.equals("灵心") && dto.getNpcName().equals("周疆主") && dto.getFavorability() == 100) {
                dto.setDesc("可前往找到周疆主，抉择是否要与他进行再续前缘任务");
            }

            if (dto.getNpcName().equals("乔乔")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与乔乔好感度已低至10，若与乔乔好感度继续下降，在百荟铺购买任意商品将支付两倍价格！");
                } else if (dto.getFavorability() >= 80) {
                    dto.setDesc("恭喜与乔乔好感度已达80，可获得在百荟铺购买商品享受优惠的特权！");
                }
            }

            if (dto.getNpcName().equals("薛瑞")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与薛瑞好感度已低至10，若与薛瑞好感度持续下降，在凝宝斋的任意交易将付出双倍代价！");
                }
            }

            if (dto.getNpcName().equals("寒星")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与寒星好感度已低至10，若与寒星好感度继续下降，在星焰将会受到意想不到的代价或惩罚！");
                }
            }

            if (dto.getNpcName().equals("倏")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与倏好感度已低至10，若与倏好感度继续下降，将失去在益寿堂降低嫌疑值的机会！");
                }
            }

            if (dto.getNpcName().equals("姜良")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与姜良好感度已低至10，若与姜良好感度继续下降，将在姜氏铁铺面临意想不到的代价或惩罚！");
                }
            }


            if (dto.getNpcName().equals("玉儿")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与玉儿好感度已低至10，若与玉儿好感度继续下降，将在醉尘阁面临意想不到的代价或惩罚！");
                }
            }


            if (dto.getNpcName().equals("天华")) {
                if (dto.getFavorability() <= 10) {
                    dto.setDesc("警告与天华好感度已低至10，若与天华好感度继续下降，将在乐坊面临意想不到的代价或惩罚！");
                }
            }

            dtoList.add(dto);
        }

        return dtoList;
    }
}
