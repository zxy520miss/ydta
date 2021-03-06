package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Clew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DbRepository extends JpaRepository<Clew,Integer>, JpaSpecificationExecutor<Clew> {

    @Query(value = "truncate table nuo_statistics;", nativeQuery = true)
    @Modifying
    void resetStatistics();

    @Query(value = "update nuo_stage set status = 0;", nativeQuery = true)
    @Modifying
    void resetStage();

    @Query(value = "update nuo_stage set status = 1 where id = 1;", nativeQuery = true)
    @Modifying
    void updateStage();

    @Query(value = "truncate table nuo_rolenpc;", nativeQuery = true)
    @Modifying
    void resetRoelNpc();

    @Query(value = "INSERT INTO `ydta`.`nuo_rolenpc`(`id`, `createdTime`, `updatedTime`, `version`, `favorability`, `npcId`, `roleId`, `npcName`, `roleName`) VALUES (1, NULL, NULL, 'v1', 40, 1, 1, '玉儿', '胡青'),\n" +
            "(2, NULL, NULL, 'v1', 30, 1, 2, '玉儿', '姜亦'),\n" +
            "(3, NULL, NULL, 'v1', 30, 1, 3, '玉儿', '灵欢'),\n" +
            "(4, NULL, NULL, 'v1', 40, 1, 4, '玉儿', '蔡瑾'),\n" +
            "(5, NULL, NULL, 'v1', 40, 1, 5, '玉儿', '山望'),\n" +
            "(6, NULL, NULL, 'v1', 60, 1, 6, '玉儿', '璇儿'),\n" +
            "(7, NULL, NULL, 'v1', 40, 1, 7, '玉儿', '万福'),\n" +
            "(8, NULL, NULL, 'v1', 30, 1, 8, '玉儿', '姜城'),\n" +
            "(9, NULL, NULL, 'v1', 30, 1, 9, '玉儿', '叶舒'),\n" +
            "(10, NULL, NULL, 'v1', 20, 1, 10, '玉儿', '芒云'),\n" +
            "(11, NULL, NULL, 'v1', 50, 1, 11, '玉儿', '君君'),\n" +
            "(12, NULL, NULL, 'v1', 30, 1, 12, '玉儿', '灵心'),\n" +
            "(13, NULL, NULL, 'v1', 30, 1, 13, '玉儿', '宇文杰'),\n" +
            "(14, NULL, NULL, 'v1', 30, 1, 14, '玉儿', '灵禾'),\n" +
            "(15, NULL, NULL, 'v1', 30, 1, 15, '玉儿', '无惧'),\n" +
            "(16, NULL, NULL, 'v1', 50, 1, 16, '玉儿', '芸娘'),\n" +
            "(17, NULL, NULL, 'v1', 30, 2, 1, '倏', '胡青'),\n" +
            "(18, NULL, NULL, 'v1', 30, 2, 2, '倏', '姜亦'),\n" +
            "(19, NULL, NULL, 'v1', 30, 2, 3, '倏', '灵欢'),\n" +
            "(20, NULL, NULL, 'v1', 30, 2, 4, '倏', '蔡瑾'),\n" +
            "(21, NULL, NULL, 'v1', 30, 2, 5, '倏', '山望'),\n" +
            "(22, NULL, NULL, 'v1', 30, 2, 6, '倏', '璇儿'),\n" +
            "(23, NULL, NULL, 'v1', 30, 2, 7, '倏', '万福'),\n" +
            "(24, NULL, NULL, 'v1', 30, 2, 8, '倏', '姜城'),\n" +
            "(25, NULL, NULL, 'v1', 30, 2, 9, '倏', '叶舒'),\n" +
            "(26, NULL, NULL, 'v1', 30, 2, 10, '倏', '芒云'),\n" +
            "(27, NULL, NULL, 'v1', 30, 2, 11, '倏', '君君'),\n" +
            "(28, NULL, NULL, 'v1', 30, 2, 12, '倏', '灵心'),\n" +
            "(29, NULL, NULL, 'v1', 30, 2, 13, '倏', '宇文杰'),\n" +
            "(30, NULL, NULL, 'v1', 30, 2, 14, '倏', '灵禾'),\n" +
            "(31, NULL, NULL, 'v1', 30, 2, 15, '倏', '无惧'),\n" +
            "(32, NULL, NULL, 'v1', 30, 2, 16, '倏', '芸娘'),\n" +
            "(33, NULL, NULL, 'v1', 30, 3, 1, '天华', '胡青'),\n" +
            "(34, NULL, NULL, 'v1', 40, 3, 2, '天华', '姜亦'),\n" +
            "(35, NULL, NULL, 'v1', 30, 3, 3, '天华', '灵欢'),\n" +
            "(36, NULL, NULL, 'v1', 40, 3, 4, '天华', '蔡瑾'),\n" +
            "(37, NULL, NULL, 'v1', 40, 3, 5, '天华', '山望'),\n" +
            "(38, NULL, NULL, 'v1', 30, 3, 6, '天华', '璇儿'),\n" +
            "(39, NULL, NULL, 'v1', 40, 3, 7, '天华', '万福'),\n" +
            "(40, NULL, NULL, 'v1', 30, 3, 8, '天华', '姜城'),\n" +
            "(41, NULL, NULL, 'v1', 40, 3, 9, '天华', '叶舒'),\n" +
            "(42, NULL, NULL, 'v1', 20, 3, 10, '天华', '芒云'),\n" +
            "(43, NULL, NULL, 'v1', 30, 3, 11, '天华', '君君'),\n" +
            "(44, NULL, NULL, 'v1', 40, 3, 12, '天华', '灵心'),\n" +
            "(45, NULL, NULL, 'v1', 30, 3, 13, '天华', '宇文杰'),\n" +
            "(46, NULL, NULL, 'v1', 30, 3, 14, '天华', '灵禾'),\n" +
            "(47, NULL, NULL, 'v1', 40, 3, 15, '天华', '无惧'),\n" +
            "(48, NULL, NULL, 'v1', 40, 3, 16, '天华', '芸娘'),\n" +
            "(49, NULL, NULL, 'v1', 30, 4, 1, '寒星', '胡青'),\n" +
            "(50, NULL, NULL, 'v1', 30, 4, 2, '寒星', '姜亦'),\n" +
            "(51, NULL, NULL, 'v1', 30, 4, 3, '寒星', '灵欢'),\n" +
            "(52, NULL, NULL, 'v1', 40, 4, 4, '寒星', '蔡瑾'),\n" +
            "(53, NULL, NULL, 'v1', 30, 4, 5, '寒星', '山望'),\n" +
            "(54, NULL, NULL, 'v1', 30, 4, 6, '寒星', '璇儿'),\n" +
            "(55, NULL, NULL, 'v1', 50, 4, 7, '寒星', '万福'),\n" +
            "(56, NULL, NULL, 'v1', 30, 4, 8, '寒星', '姜城'),\n" +
            "(57, NULL, NULL, 'v1', 30, 4, 9, '寒星', '叶舒'),\n" +
            "(58, NULL, NULL, 'v1', 20, 4, 10, '寒星', '芒云'),\n" +
            "(59, NULL, NULL, 'v1', 30, 4, 11, '寒星', '君君'),\n" +
            "(60, NULL, NULL, 'v1', 30, 4, 12, '寒星', '灵心'),\n" +
            "(61, NULL, NULL, 'v1', 10, 4, 13, '寒星', '宇文杰'),\n" +
            "(62, NULL, NULL, 'v1', 40, 4, 14, '寒星', '灵禾'),\n" +
            "(63, NULL, NULL, 'v1', 30, 4, 15, '寒星', '无惧'),\n" +
            "(64, NULL, NULL, 'v1', 30, 4, 16, '寒星', '芸娘'),\n" +
            "(65, NULL, NULL, 'v1', 30, 5, 1, '薛瑞', '胡青'),\n" +
            "(66, NULL, NULL, 'v1', 40, 5, 2, '薛瑞', '姜亦'),\n" +
            "(67, NULL, NULL, 'v1', 30, 5, 3, '薛瑞', '灵欢'),\n" +
            "(68, NULL, NULL, 'v1', 40, 5, 4, '薛瑞', '蔡瑾'),\n" +
            "(69, NULL, NULL, 'v1', 30, 5, 5, '薛瑞', '山望'),\n" +
            "(70, NULL, NULL, 'v1', 30, 5, 6, '薛瑞', '璇儿'),\n" +
            "(71, NULL, NULL, 'v1', 30, 5, 7, '薛瑞', '万福'),\n" +
            "(72, NULL, NULL, 'v1', 50, 5, 8, '薛瑞', '姜城'),\n" +
            "(73, NULL, NULL, 'v1', 10, 5, 9, '薛瑞', '叶舒'),\n" +
            "(74, NULL, NULL, 'v1', 20, 5, 10, '薛瑞', '芒云'),\n" +
            "(75, NULL, NULL, 'v1', 30, 5, 11, '薛瑞', '君君'),\n" +
            "(76, NULL, NULL, 'v1', 40, 5, 12, '薛瑞', '灵心'),\n" +
            "(77, NULL, NULL, 'v1', 10, 5, 13, '薛瑞', '宇文杰'),\n" +
            "(78, NULL, NULL, 'v1', 40, 5, 14, '薛瑞', '灵禾'),\n" +
            "(79, NULL, NULL, 'v1', 30, 5, 15, '薛瑞', '无惧'),\n" +
            "(80, NULL, NULL, 'v1', 30, 5, 16, '薛瑞', '芸娘'),\n" +
            "(81, NULL, NULL, 'v1', 30, 6, 1, '乔乔', '胡青'),\n" +
            "(82, NULL, NULL, 'v1', 70, 6, 2, '乔乔', '姜亦'),\n" +
            "(83, NULL, NULL, 'v1', 30, 6, 3, '乔乔', '灵欢'),\n" +
            "(84, NULL, NULL, 'v1', 40, 6, 4, '乔乔', '蔡瑾'),\n" +
            "(85, NULL, NULL, 'v1', 30, 6, 5, '乔乔', '山望'),\n" +
            "(86, NULL, NULL, 'v1', 40, 6, 6, '乔乔', '璇儿'),\n" +
            "(87, NULL, NULL, 'v1', 30, 6, 7, '乔乔', '万福'),\n" +
            "(88, NULL, NULL, 'v1', 40, 6, 8, '乔乔', '姜城'),\n" +
            "(89, NULL, NULL, 'v1', 10, 6, 9, '乔乔', '叶舒'),\n" +
            "(90, NULL, NULL, 'v1', 40, 6, 10, '乔乔', '芒云'),\n" +
            "(91, NULL, NULL, 'v1', 30, 6, 11, '乔乔', '君君'),\n" +
            "(92, NULL, NULL, 'v1', 30, 6, 12, '乔乔', '灵心'),\n" +
            "(93, NULL, NULL, 'v1', 30, 6, 13, '乔乔', '宇文杰'),\n" +
            "(94, NULL, NULL, 'v1', 30, 6, 14, '乔乔', '灵禾'),\n" +
            "(95, NULL, NULL, 'v1', 30, 6, 15, '乔乔', '无惧'),\n" +
            "(96, NULL, NULL, 'v1', 20, 6, 16, '乔乔', '芸娘'),\n" +
            "(97, NULL, NULL, 'v1', 30, 7, 1, '姜良', '胡青'),\n" +
            "(98, NULL, NULL, 'v1', 50, 7, 2, '姜良', '姜亦'),\n" +
            "(99, NULL, NULL, 'v1', 40, 7, 3, '姜良', '灵欢'),\n" +
            "(100, NULL, NULL, 'v1', 30, 7, 4, '姜良', '蔡瑾'),\n" +
            "(101, NULL, NULL, 'v1', 30, 7, 5, '姜良', '山望'),\n" +
            "(102, NULL, NULL, 'v1', 30, 7, 6, '姜良', '璇儿'),\n" +
            "(103, NULL, NULL, 'v1', 30, 7, 7, '姜良', '万福'),\n" +
            "(104, NULL, NULL, 'v1', 20, 7, 8, '姜良', '姜城'),\n" +
            "(105, NULL, NULL, 'v1', 20, 7, 9, '姜良', '叶舒'),\n" +
            "(106, NULL, NULL, 'v1', 50, 7, 10, '姜良', '芒云'),\n" +
            "(107, NULL, NULL, 'v1', 30, 7, 11, '姜良', '君君'),\n" +
            "(108, NULL, NULL, 'v1', 30, 7, 12, '姜良', '灵心'),\n" +
            "(109, NULL, NULL, 'v1', 30, 7, 13, '姜良', '宇文杰'),\n" +
            "(110, NULL, NULL, 'v1', 30, 7, 14, '姜良', '灵禾'),\n" +
            "(111, NULL, NULL, 'v1', 30, 7, 15, '姜良', '无惧'),\n" +
            "(112, NULL, NULL, 'v1', 30, 7, 16, '姜良', '芸娘'),\n" +
            "(113, NULL, NULL, 'v1', 40, 8, 1, '周疆主', '胡青'),\n" +
            "(114, NULL, NULL, 'v1', 40, 8, 2, '周疆主', '姜亦'),\n" +
            "(115, NULL, NULL, 'v1', 30, 8, 3, '周疆主', '灵欢'),\n" +
            "(116, NULL, NULL, 'v1', 30, 8, 4, '周疆主', '蔡瑾'),\n" +
            "(117, NULL, NULL, 'v1', 10, 8, 5, '周疆主', '山望'),\n" +
            "(118, NULL, NULL, 'v1', 30, 8, 6, '周疆主', '璇儿'),\n" +
            "(119, NULL, NULL, 'v1', 30, 8, 7, '周疆主', '万福'),\n" +
            "(120, NULL, NULL, 'v1', 50, 8, 8, '周疆主', '姜城'),\n" +
            "(121, NULL, NULL, 'v1', 20, 8, 9, '周疆主', '叶舒'),\n" +
            "(122, NULL, NULL, 'v1', 80, 8, 10, '周疆主', '芒云'),\n" +
            "(123, NULL, NULL, 'v1', 30, 8, 11, '周疆主', '君君'),\n" +
            "(124, NULL, NULL, 'v1', 70, 8, 12, '周疆主', '灵心'),\n" +
            "(125, NULL, NULL, 'v1', 50, 8, 13, '周疆主', '宇文杰'),\n" +
            "(126, NULL, NULL, 'v1', 30, 8, 14, '周疆主', '灵禾'),\n" +
            "(127, NULL, NULL, 'v1', 30, 8, 15, '周疆主', '无惧'),\n" +
            "(128, NULL, NULL, 'v1', 30, 8, 16, '周疆主', '芸娘');", nativeQuery = true)
    @Modifying
    void insertRoleNpc();


    @Query(value = "truncate table nuo_messagepush;", nativeQuery = true)
    @Modifying
    void resetMessagePush();

    @Query(value = "UPDATE nuo_role set suspicion = 0 , halo = 0;", nativeQuery = true)
    @Modifying
    void resetRole();

    @Query(value = "UPDATE nuo_role set halo = 20 WHERE id in(3,12,14);",nativeQuery = true)
    @Modifying
    void insertRole();

    @Query(value = "UPDATE nuo_role set camp = 0 where id in (1,2,3,4,5,6,9,10,13,14,16)" ,nativeQuery = true)
    @Modifying
    void  updateCamp();

    @Query(value = "UPDATE nuo_role set camp = 3 where id in (8,15)" ,nativeQuery = true)
    @Modifying
    void  updateCamp1();

    @Query(value = "UPDATE nuo_role set camp = 1 where id = 7" ,nativeQuery = true)
    @Modifying
    void  updateCamp2();

    @Query(value = "UPDATE nuo_role set camp = 2 where id = 11" ,nativeQuery = true)
    @Modifying
    void  updateCamp3();

    @Query(value = "UPDATE nuo_role set camp = 4 where id = 12" ,nativeQuery = true)
    @Modifying
    void  updateCamp4();

    @Query(value = "truncate table nuo_clew;", nativeQuery = true)
    @Modifying
    void resetClew();

    @Query(value = "INSERT INTO `ydta`.`nuo_clew`(`id`, `createdTime`, `updatedTime`, `version`, `description`, `roleId`, `stageId`, `status`) VALUES (1, NULL, NULL, 'v1', '两条沾染干涸已久血迹的项链', 10, 1, 1),\n" +
            "(2, NULL, NULL, 'v1', '灵龟甲', 10, 1, 1),\n" +
            "(3, NULL, NULL, 'v1', '一张无惧寄给你，标注了多个可能是盛放圣器地址的地图', 10, 1, 1),\n" +
            "(4, NULL, NULL, 'v1', '对灵禾的人生期待计划书', 10, 1, 1),\n" +
            "(5, NULL, NULL, 'v1', '万氏的传家宝', 10, 1, 1),\n" +
            "(6, NULL, NULL, 'v1', '万铁衣的一封遗书', 10, 1, 1),\n" +
            "(7, NULL, NULL, 'v1', '偷偷拿走的灵欢与她心上人的信物', 10, 1, 1),\n" +
            "(8, NULL, NULL, 'v1', '日记', 10, 1, 1),\n" +
            "(9, NULL, NULL, 'v1', '你和万铁衣的双人画像', 10, 1, 1),\n" +
            "(10, NULL, NULL, 'v1', '和芒云一模一样的一些配饰', 10, 1, 1),\n" +
            "(11, NULL, NULL, 'v1', '一朵失去叶子的芙蓉花中间有蓝色粉末', 12, 1, 1),\n" +
            "(12, NULL, NULL, 'v1', '周疆主救下你那天，你手上留下的伤疤', 12, 1, 1),\n" +
            "(13, NULL, NULL, 'v1', '刻有黎语“容”字的木钗', 12, 1, 1),\n" +
            "(14, NULL, NULL, 'v1', '黎族字典', 12, 1, 1),\n" +
            "(15, NULL, NULL, 'v1', '黎族威胁你的密信', 12, 1, 1),\n" +
            "(16, NULL, NULL, 'v1', '你引荐胡青成为祭祀候选人的昭告', 12, 1, 1),\n" +
            "(17, NULL, NULL, 'v1', '胡青与周容的指婚文书密函，还未昭告', 12, 1, 1),\n" +
            "(18, NULL, NULL, 'v1', '帮助宇文杰完成他的研究项目的凝宝斋购物清单', 12, 1, 1),\n" +
            "(19, NULL, NULL, 'v1', '周容身上荷包装有一枚圣器碎片', 12, 1, 1),\n" +
            "(20, NULL, NULL, 'v1', '你来到夏族之时抽取的一罐不同于夏族人的血', 12, 1, 1),\n" +
            "(21, NULL, NULL, 'v1', '你写给玉渊芒依的暗信，结尾都会关心姐姐的手', 12, 1, 1),\n" +
            "(22, NULL, NULL, 'v1', '周疆主为你作的肖像画、送的绸缎布匹等礼物', 12, 1, 1);", nativeQuery = true)
    @Modifying
    void insertClew();

    @Query(value = "update nuo_play set status = 0;", nativeQuery = true)
    @Modifying
    void resetPlay();

    @Query(value = "truncate table nuo_task;", nativeQuery = true)
    @Modifying
    void resetTask();


    @Query(value = "INSERT INTO `ydta`.`nuo_task`(`id`, `createdTime`, `updatedTime`, `description`, `roleId`, `stageId`, `status`, `version`) VALUES \n" +
            "(1, NULL, '2020-01-08 08:48:25', '查出杀害冯律司的凶手', 0, 2, 0, ''),\n" +
            "(2, NULL, '2020-01-08 09:00:54', '查出杀害小白的凶手', 0, 4, 0, ''),\n" +
            "(3, NULL, '2020-01-08 09:00:56', '查出杀害周疆主的凶手 ', 0, 5, 0, ''),\n" +
            "(4, NULL, '2020-01-08 09:00:54', '全程隐藏自己是杀害小白的凶手一事', 15, 4, 0, ''),\n" +
            "(5, NULL, NULL, '尽量获得更多的圣器碎片真迹', 0, 9, 0, ''),\n" +
            "(6, NULL, '2020-01-08 09:00:58', '尽量先人一步集齐圣器碎片', 0, 6, 0, ''),\n" +
            "(7, NULL, '2020-01-08 09:00:58', '做出阵营的抉择', 0, 6, 0, ''),\n" +
            "(8, NULL, '2020-01-08 09:00:58', '继续调查凶手', 0, 6, 0, ''),\n" +
            "(9, NULL, NULL, '隐藏无惧是你的内应一事', 12, 1, 1, NULL),\n" +
            "(10, NULL, NULL, '找到周容的线索', 10, 1, 1, NULL),\n" +
            "(11, NULL, NULL, '为了报恩，找到黑风师傅提到的脚踝有花朵印记的女子，并找到解救她的办法，', 14, 1, 1, ''),\n" +
            "(12, NULL, NULL, '隐藏你是仿造圣器碎片之人的事情', 7, 1, 1, NULL),\n" +
            "(13, NULL, NULL, '隐藏娘亲是青楼女子一事', 13, 1, 1, NULL),\n" +
            "(14, NULL, NULL, '隐藏你在周容事件中撒谎而害了山望一事', 1, 1, 1, NULL),\n" +
            "(15, NULL, NULL, '尽量协助山望找到周容下落', 16, 1, 1, NULL),\n" +
            "(16, NULL, NULL, '全程隐藏你是杀害冯律司的凶手', 3, 1, 1, NULL),\n" +
            "(17, NULL, NULL, '隐藏你是“黄帮”势力真实老大这件事', 8, 1, 1, NULL),\n" +
            "(18, NULL, NULL, '隐藏你是当年写匿名举报信的人之事', 2, 1, 1, NULL),\n" +
            "(19, NULL, NULL, '找到周容的下落', 5, 1, 1, NULL),\n" +
            "(20, NULL, NULL, '尽量协助山望找到周容下落', 6, 1, 1, NULL),\n" +
            "(21, NULL, NULL, '隐藏你的黎族身份', 11, 1, 1, NULL),\n" +
            "(22, NULL, NULL, '隐藏你是当年纵火之人，逃避通缉', 4, 1, 1, NULL),\n" +
            "(23, NULL, NULL, '隐藏你是灵心留在夏族的内应一事', 15, 1, 1, NULL),\n" +
            "(24, NULL, NULL, '隐藏你在密室设置了行凶机关一事', 9, 1, 1, NULL),\n" +
            "(25, NULL, NULL, '找到无惧共同寻找剩余圣器碎片', 12, 1, 1, NULL),\n" +
            "(26, NULL, NULL, '找到你放在周容身上的圣器碎片', 10, 1, 1, NULL),\n" +
            "(27, NULL, NULL, '隐藏你的黎族身份', 10, 1, 1, NULL),\n" +
            "(28, NULL, NULL, '调查娘亲——万静之死的真相', 14, 1, 1, NULL),\n" +
            "(29, NULL, NULL, '调查娘亲——万静之死的真相', 7, 1, 1, NULL),\n" +
            "(30, NULL, NULL, '调查当年条草事件真相', 7, 1, 1, NULL),\n" +
            "(31, NULL, NULL, '为了立功，调查当年纵火之事的真相', 13, 1, 1, NULL),\n" +
            "(32, NULL, NULL, '调查当年条草事件真相', 1, 1, 1, NULL),\n" +
            "(33, NULL, NULL, '为了立功，调查当年纵火之事的真相', 1, 1, 1, NULL),\n" +
            "(34, NULL, NULL, '隐藏你和山望（周心）的真实身份', 16, 1, 1, NULL),\n" +
            "(35, NULL, NULL, '调查当年是谁多次举报醉尘阁，引发琴川之死\\r\\n\\n', 16, 1, 1, NULL),\n" +
            "(36, NULL, NULL, '调查父亲当年被判罚的真相', 3, 1, 1, NULL),\n" +
            "(37, NULL, NULL, '调查条草事件真相\\r\\n\\n', 3, 1, 1, NULL),\n" +
            "(38, NULL, NULL, '调查当年是谁多次举报醉尘阁，引发琴川之死', 5, 1, 1, NULL),\n" +
            "(39, NULL, NULL, '找寻关于父亲的线索\\r\\n\\n', 5, 1, 1, NULL),\n" +
            "(40, NULL, NULL, '找机会和方法将姜城带到姜氏铁铺给他下毒复仇', 11, 1, 1, NULL),\n" +
            "(41, NULL, NULL, '隐藏你绑架了周容之事', 11, 1, 1, NULL),\n" +
            "(42, NULL, NULL, '调查清楚胡青在周容失踪当日到底看见了什么，并给周疆主汇报了什么', 11, 1, 1, NULL),\n" +
            "(43, NULL, NULL, '隐藏你是条草事件真凶一事', 4, 1, 1, NULL),\n" +
            "(44, NULL, NULL, '调查清楚你的恩师——林祭司之案的真相', 4, 1, 1, NULL),\n" +
            "(45, NULL, NULL, '同灵心接头，共同找寻圣器碎片的下落', 15, 1, 1, NULL),\n" +
            "(46, NULL, NULL, '调查自己的身世之谜', 15, 1, 1, NULL),\n" +
            "(47, NULL, NULL, '在晚饭前，将你准备好的圣器碎片私下交给周疆主，让他能顺利在初戌之时前往密室触发机关（若玩家没有亲手操作，也将视为操作，会有NPC前来帮助），如果东窗事发，全程隐藏你是凶手一事', 9, 1, 1, NULL),\n" +
            "(48, NULL, NULL, '在你认为合适的时机，可以选择向芸娘表露心意，获得一纸婚书', 9, 1, 1, NULL),\n" +
            "(49, NULL, NULL, '隐藏你是谋害万静的真凶一事', 8, 1, 1, NULL);", nativeQuery = true)
    @Modifying
    void insertTask();




}
