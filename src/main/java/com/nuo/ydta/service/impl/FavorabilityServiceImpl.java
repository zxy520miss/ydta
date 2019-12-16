package com.nuo.ydta.service.impl;

import com.google.common.collect.Lists;
import com.nuo.ydta.domain.PushBean;
import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.dto.RoleNpcDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.FavorabilityRepository;
import com.nuo.ydta.service.FavorabilityService;
import com.nuo.ydta.service.JiGuangPushService;
import com.nuo.ydta.service.PushService;
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
    private JiGuangPushService jiGuangPushService;

    @Autowired
    private PushService pushService;

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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 0;
                    List<RoleNpcDto> dtoList = Lists.newArrayList();
                    List<RoleNpc> roleNpcs = favorabilityRepository.findByRoleName(roleName);
                    for (RoleNpc roleNpc : roleNpcs) {
                        RoleNpcDto dto = new RoleNpcDto();
                        BeanUtils.copyProperties(roleNpc, dto);
                        if (!dto.getNpcName().equals("周疆主")) {
                            if (dto.getFavorability() >= 90) {
                                switch (roleName) {
                                    case "乔乔":
                                    case "姜良":
                                    case "寒星":
                                    case "薛瑞":
                                    case "倏":
                                    case "玉儿":
                                    case "天华":
                                        dto.setDesc("恭喜与" + roleName + "好感度已达90，可前往夏晗居接取义结金兰或百年好合任务");
                                        pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("与周疆主好感度已低至10，若与周疆主好感度继续下降，将面临杖刑责罚！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }

                        if (roleName.equals("灵心") && dto.getNpcName().equals("周疆主") && dto.getFavorability() == 100) {
                            dto.setDesc("可前往找到周疆主，抉择是否要与他进行再续前缘任务");
                            pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                        }

                        if (dto.getNpcName().equals("乔乔")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("警告与乔乔好感度已低至10，若与乔乔好感度继续下降，在百荟铺购买任意商品将支付两倍价格！");
                            } else if (dto.getFavorability() >= 80) {
                                dto.setDesc("恭喜与乔乔好感度已达80，可获得在百荟铺购买商品享受优惠的特权！");
                            }
                            pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                        }

                        if (dto.getNpcName().equals("薛瑞")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("警告与薛瑞好感度已低至10，若与薛瑞好感度持续下降，在凝宝斋的任意交易将付出双倍代价！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }

                        if (dto.getNpcName().equals("寒星")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("恭喜与寒星好感度已低至10，若与寒星好感度继续下降，在星焰将会受到意想不到的代价或惩罚！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }

                        if (dto.getNpcName().equals("倏")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("警告与倏好感度已低至10，若与倏好感度继续下降，将失去在益寿堂降低嫌疑值的机会！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }

                        if (dto.getNpcName().equals("姜良")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("警告与姜良好感度已低至10，若与姜良好感度继续下降，将在姜氏铁铺面临意想不到的代价或惩罚！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }


                        if (dto.getNpcName().equals("玉儿")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("警告与玉儿好感度已低至10，若与玉儿好感度继续下降，将在醉尘阁面临意想不到的代价或惩罚！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }


                        if (dto.getNpcName().equals("天华")) {
                            if (dto.getFavorability() <= 10) {
                                dto.setDesc("警告与天华好感度已低至10，若与天华好感度继续下降，将在乐坊面临意想不到的代价或惩罚！");
                                pushService.push("壹點探案---好感度", dto.getDesc(), roleNpc.getRoleId(), roleNpc.getNpcName());
                            }
                        }

                        if (dto.getNpcName().equals("周疆主") && dto.getFavorability() >= 60) {
                            count++;
                        }

                        if (!dto.getNpcName().equals("周疆主") && dto.getFavorability() >= 80) {
                            count++;
                        }

                        dtoList.add(dto);
                    }

                    if (count == 7) {
                        PushBean pushBean = new PushBean();
                        pushBean.setAlert("恭喜解锁隐藏成就‘众星捧月’，可找到倏，了解被人拥戴之后的特权！");
                        pushService.push("壹點探案---好感度", pushBean.getAlert(), rn.getRoleId(), rn.getNpcName());
                    }
                }
            }).start();
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
                    switch (roleName) {
                        case "乔乔":
                        case "姜良":
                        case "寒星":
                        case "薛瑞":
                        case "倏":
                        case "玉儿":
                        case "天华":
                            dto.setDesc("恭喜与" + roleName + "好感度已达90，可前往夏晗居接取义结金兰或百年好合任务");
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
