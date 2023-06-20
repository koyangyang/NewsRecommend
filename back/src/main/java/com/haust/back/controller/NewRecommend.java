package com.haust.back.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.haust.back.entity.New;
import com.haust.back.entity.Useroperationnew;
import com.haust.back.mapper.NewMapper;
import com.haust.back.mapper.UseroperationnewMapper;

import io.swagger.annotations.ApiOperation;

@RestController
public class NewRecommend {
    // 注入用户操作新闻表的Mapper
    @Autowired
    private UseroperationnewMapper useroperationnewMapper;
    // 注入新闻表的Mapper
    @Autowired
    private NewMapper newMapper;

    @ApiOperation("基于协同过滤算法，对用户ID为{ userId }的指定用户推荐{ size }个新闻")
    @GetMapping("/recommend/{userId}/{size}")
    public List<New> recommend(@PathVariable Integer userId, @PathVariable Integer size) throws TasteException {
        // 获取所有用户的操作新闻
        List<Useroperationnew> userList = useroperationnewMapper.getAllUserPreference();
        // 创建数据模型
        DataModel dataModel = this.createDataModel(userList);
        // 获取用户相似程度，使用余弦相似度
        UserSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
        // 获取用户邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);
        // 构建推荐器
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        // 推荐{ size }条新闻
        List<RecommendedItem> recommendedItems = recommender.recommend(userId, size);
        // 获取计算新闻得分
        List<Float> itemScores = recommendedItems.stream().map(RecommendedItem::getValue).collect(Collectors.toList());
        // 获取新闻ID
        List<Long> itemIds = recommendedItems.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());
        for (int i = 0; i < itemIds.size(); i++) {
            System.out.println("推荐新闻ID：" + itemIds.get(i) + " 推荐新闻分数：" + itemScores.get(i));
        }
        if (itemIds.size() == 0) {
            System.out.println("数据不足无法产生推荐");
            return null;
        } else
            // 根据新闻ID查询新闻的详细内容并返回
        {
            return newMapper.selectBatchIds(itemIds);
        }
    }

    private DataModel createDataModel(List<Useroperationnew> Useroperationnews) {
        // 创建数据模型
        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
        // 根据用户ID分组
        Map<Integer, List<Useroperationnew>> map =
            Useroperationnews.stream().collect(Collectors.groupingBy(Useroperationnew::getUserId));
        Collection<List<Useroperationnew>> list = map.values();
        // 遍历分组后的数据
        for (List<Useroperationnew> userPreferences : list) {
            // 创建用户偏好数组
            GenericPreference[] array = new GenericPreference[userPreferences.size()];
            // 遍历用户偏好
            for (int i = 0; i < userPreferences.size(); i++) {
                Useroperationnew userPreference = userPreferences.get(i);
                GenericPreference item = new GenericPreference(userPreference.getUserId(), userPreference.getNewId(),
                    userPreference.getValue());
                array[i] = item;
            }
            // 将用户偏好数组放入数据模型中
            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
        }
        // 返回数据模型
        return new GenericDataModel(fastByIdMap);
    }

    @ApiOperation("获取所有用户的推荐新闻")
    @GetMapping("/recommend/hot")
    public List<New> getHotNews() {
        // 获取所有用户的操作新闻，得到热门新闻
        List<Useroperationnew> hotsnews = useroperationnewMapper.getHotNews();
        // 获取新闻ID
        List<Integer> itemIds = hotsnews.stream().map(Useroperationnew::getNewId).collect(Collectors.toList());
        // 获取新闻得分
        if (itemIds.size() == 0) {
            System.out.println("数据不足无法产生推荐");
            return null;
        } else {
            for (int i = 0; i < itemIds.size(); i++) {
                System.out.println("排名第" + (i + 1) + "的新闻ID是：" + itemIds.get(i));
            }
        }
            // 根据新闻ID查询新闻的详细内容并返回
        return newMapper.selectBatchIds(itemIds);
    }
}