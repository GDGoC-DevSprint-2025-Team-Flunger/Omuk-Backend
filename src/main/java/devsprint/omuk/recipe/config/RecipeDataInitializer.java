package devsprint.omuk.recipe.config;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.repository.RecipeRepository;
import devsprint.omuk.recipe.service.RecipeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RecipeDataInitializer {

    private final RecipeRepository recipeRepository;

    private final RecipeService recipeService;

    @PostConstruct
    public void init() {
        if (recipeRepository.count() > 0) return;

        List<Recipe> recipes = List.of(
                Recipe.builder().title("김치볶음밥")
                        .difficulty(2)
                        .cookTime(15)
                        .mealTimes(List.of(MealTime.LUNCH))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SPICY, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.EGG))
                        .ingredients(List.of("김치", "밥", "햄", "치즈"))
                        .steps(List.of("김치를 작게 썬다.", "햄과 치즈를 준비한다.", "팬에 김치를 볶는다.", "밥과 햄을 넣고 함께 볶는다.", "치즈를 얹고 섞은 뒤 완성한다."))
                        .substitutes(Map.of("김치", "깍두기"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2023/08/26/41eb2344b4e6a719c209ca55ed91c3f31.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=김치볶음밥")
                        .averageRating(4.5)
                        .build(),

                Recipe.builder().title("김치찌개")
                        .difficulty(3)
                        .cookTime(25)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SPICY, TasteType.SALTY))
                        .allergyTags(List.of())
                        .ingredients(List.of("김치", "돼지고기", "두부", "파", "마늘", "고춧가루", "간장", "된장", "설탕"))
                        .steps(List.of("김치를 적당한 크기로 썬다.", "고춧가루, 간장, 된장, 설탕을 섞어 양념장을 만든다.", "냄비에 돼지고기와 마늘을 먼저 볶는다.", "김치를 넣고 함께 볶는다.", "물을 넣고 양념장을 풀어 끓인다.", "두부와 파를 넣고 마무리한다."))
                        .substitutes(Map.of("돼지고기", "참치"))
                        .imageUrl("https://cdn.pixabay.com/photo/2022/12/29/01/01/food-7683985_1280.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=김치찌개")
                        .averageRating(4.3)
                        .build(),

                Recipe.builder().title("콩나물무침")
                        .difficulty(1)
                        .cookTime(10)
                        .mealTimes(List.of(MealTime.BREAKFAST, MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SALTY))
                        .allergyTags(List.of())
                        .ingredients(List.of("콩나물", "다진 마늘", "참기름", "소금", "깨"))
                        .steps(List.of("콩나물을 깨끗이 씻는다.", "끓는 물에 콩나물을 3~4분 데친다.", "찬물에 헹군 후 물기를 꼭 짠다.", "다진 마늘, 소금, 참기름, 깨를 넣고 무친다."))
                        .substitutes(Map.of("참기름", "들기름"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2023/08/28/b5dad941f601b53edbc06d8622dcdfe81.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=콩나물무침")
                        .averageRating(4.2)
                        .build(),

                Recipe.builder().title("떡볶이")
                        .difficulty(3)
                        .cookTime(20)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER, MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SPICY, TasteType.SWEET, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.WHEAT))
                        .ingredients(List.of("떡", "어묵", "대파", "고추장", "고춧가루", "설탕", "간장", "마늘"))
                        .steps(List.of("고추장, 고춧가루, 설탕, 간장, 다진 마늘을 섞어 양념장을 만든다.", "팬에 물을 붓고 양념장을 넣어 끓인다.", "떡과 어묵을 넣고 중불에서 끓인다.", "국물이 걸쭉해질 때까지 저어가며 졸인다.", "대파를 넣고 한 번 더 끓인 후 완성한다."))
                        .substitutes(Map.of("어묵", "소시지"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2018/08/13/3233d427883d15239f297aeeaf1775531.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=떡볶이")
                        .averageRating(4.6)
                        .build(),

                Recipe.builder().title("프렌치토스트")
                        .difficulty(2)
                        .cookTime(15)
                        .mealTimes(List.of(MealTime.BREAKFAST, MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SWEET))
                        .allergyTags(List.of(AllergyTag.MILK, AllergyTag.EGG, AllergyTag.WHEAT))
                        .ingredients(List.of("식빵", "달걀", "우유", "설탕", "버터"))
                        .steps(List.of("달걀, 우유, 설탕을 섞어 반죽물을 만든다.", "식빵을 반죽물에 충분히 적신다.", "달군 팬에 버터를 녹인다.", "식빵을 앞뒤로 노릇하게 굽는다.", "기호에 따라 시럽이나 과일을 곁들인다."))
                        .substitutes(Map.of("우유", "두유"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2018/01/15/2a6b7fb6670b596e443c2f6f91aa3ce91.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=프렌치토스트")
                        .averageRating(4.4)
                        .build(),

                Recipe.builder().title("계란말이")
                        .difficulty(2)
                        .cookTime(10)
                        .mealTimes(List.of(MealTime.BREAKFAST, MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SAVORY, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.EGG))
                        .ingredients(List.of("계란", "당근", "대파", "소금", "식용유"))
                        .steps(List.of("계란을 깨서 풀고, 당근과 대파를 잘게 썬다.", "풀어놓은 계란에 채소와 소금을 넣고 섞는다.", "팬에 식용유를 두르고 약불에서 계란물을 부은 후 얇게 펼친다.", "계란이 익기 시작하면 천천히 말아가며 굽는다.", "끝까지 말린 뒤 식힌 후 먹기 좋게 썬다."))
                        .substitutes(Map.of("당근", "파프리카"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2019/07/29/81147460a9faf7bdb78740b34758a5651.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=계란말이")
                        .averageRating(4.5)
                        .build(),

                Recipe.builder().title("된장찌개")
                        .difficulty(3).cookTime(20)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.SPRING, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SAVORY, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.SHELLFISH))
                        .ingredients(List.of("된장", "두부", "애호박", "감자", "양파", "청양고추", "마늘", "멸치", "물"))
                        .steps(List.of("냄비에 물과 멸치를 넣고 육수를 끓인다.", "된장을 풀어 넣는다.", "감자, 애호박, 양파를 넣고 끓인다.", "두부와 청양고추, 마늘을 넣고 중불로 더 끓인다.", "모든 재료가 익으면 완성한다."))
                        .substitutes(Map.of("멸치", "다시마"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2017/04/26/ddd495fd432955701068e1a21a0d33211.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=된장찌개")
                        .averageRating(4.4)
                        .build(),

                Recipe.builder().title("팬케이크")
                        .difficulty(2)
                        .cookTime(15)
                        .mealTimes(List.of(MealTime.BREAKFAST, MealTime.LUNCH, MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SWEET))
                        .allergyTags(List.of(AllergyTag.MILK, AllergyTag.EGG, AllergyTag.WHEAT))
                        .ingredients(List.of("팬케이크 믹스", "우유", "계란", "버터", "시럽"))
                        .steps(List.of("팬케이크 믹스에 계란과 우유를 넣고 잘 섞는다.", "반죽을 10분 정도 휴지시킨다.", "팬에 버터를 녹인다.", "중불에서 반죽을 한 국자씩 떠서 노릇하게 굽는다.", "시럽이나 과일 등을 곁들여 마무리한다."))
                        .substitutes(Map.of("우유", "두유"))
                        .imageUrl("https://cdn.pixabay.com/photo/2017/05/07/11/47/food-2292169_1280.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=팬케이크")
                        .averageRating(4.7)
                        .build(),

                Recipe.builder().title("딸기빙수")
                        .difficulty(1)
                        .cookTime(10)
                        .mealTimes(List.of(MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SUMMER))
                        .tasteTags(List.of(TasteType.SWEET))
                        .allergyTags(List.of(AllergyTag.MILK))
                        .ingredients(List.of("얼음", "딸기", "연유", "우유", "설탕"))
                        .steps(List.of("딸기를 깨끗이 씻고 설탕에 재워둔다.", "우유를 얼려 곱게 간다 (또는 얼음을 사용한다).", "그릇에 간 얼음을 담는다.", "연유를 뿌리고, 절인 딸기를 올린다.", "기호에 따라 추가 토핑(아이스크림, 젤리 등)을 올려 마무리한다."))
                        .substitutes(Map.of("연유", "꿀"))
                        .imageUrl("https://img0.yna.co.kr/etc/inner/KR/2020/01/02/AKR20200102033500030_01_i_P4.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=딸기빙수")
                        .averageRating(4.8).build(),

                Recipe.builder().title("제육볶음")
                        .difficulty(4)
                        .cookTime(30)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL))
                        .tasteTags(List.of(TasteType.SPICY, TasteType.SAVORY, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.WHEAT))
                        .ingredients(List.of("돼지고기", "고추장", "간장", "설탕", "마늘", "고춧가루", "대파", "양파"))
                        .steps(List.of("고추장, 간장, 설탕, 다진 마늘, 고춧가루를 섞어 양념장을 만든다.", "돼지고기를 양념장에 재운다 (10~15분).", "팬에 기름을 두르고 재운 고기를 볶는다.", "양파, 대파를 넣고 함께 볶는다.", "고기가 익고 양파가 투명해지면 마무리한다."))
                        .substitutes(Map.of("고추장", "청양고추+된장"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2015/05/27/38013d1dfd8fa46a871b9cda074b26341.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=제육볶음")
                        .averageRating(4.6)
                        .build(),

                Recipe.builder().title("순두부찌개")
                        .difficulty(3)
                        .cookTime(20)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SPICY, TasteType.SAVORY, TasteType.UMAMI))
                        .allergyTags(List.of(AllergyTag.EGG, AllergyTag.SHELLFISH)) // 날계란 토핑 + 바지락 육수 기준
                        .ingredients(List.of("순두부", "바지락", "애호박", "양파", "청양고추", "고춧가루", "다진 마늘", "계란", "참기름", "소금"))
                        .steps(List.of("냄비에 참기름을 두르고 다진 마늘과 고춧가루를 볶아 고추기름을 만든다.", "물과 바지락을 넣고 끓여 육수를 낸다.", "양파, 애호박, 청양고추를 넣고 끓인다.", "순두부를 조심히 넣고 한소끔 끓인다.", "계란을 풀어 넣고 살짝 익힌 뒤 마무리한다."))
                        .substitutes(Map.of("바지락", "멸치육수"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2019/03/01/cbdcad39283af740afd0e08f97849c7c1.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=순두부찌개")
                        .averageRating(4.5)
                        .build(),

                Recipe.builder()
                        .title("오므라이스")
                        .difficulty(3)
                        .cookTime(25)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SAVORY, TasteType.SWEET))
                        .allergyTags(List.of(AllergyTag.EGG, AllergyTag.MILK))
                        .ingredients(List.of("밥", "계란", "양파", "당근", "햄", "케첩", "소금", "우유", "식용유"))
                        .steps(List.of("양파, 당근, 햄을 잘게 썬다.", "팬에 기름을 두르고 야채와 햄을 볶은 후 밥과 케첩을 넣고 볶는다.", "계란에 소금과 우유를 넣고 잘 푼다.", "계란물을 팬에 부어 얇게 지단을 만든다.", "볶음밥을 계란 위에 올리고 오므려 담아낸다."))
                        .substitutes(Map.of("우유", "두유"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2016/06/05/1ba7e34cf0daf694f09a3a9539ebdb161.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=오므라이스")
                        .averageRating(4.6)
                        .build(),

                Recipe.builder().title("소시지야채볶음")
                        .difficulty(2)
                        .cookTime(15)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER, MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SAVORY, TasteType.SALTY, TasteType.SWEET))
                        .allergyTags(List.of(AllergyTag.WHEAT))
                        .ingredients(List.of("소시지", "양파", "피망", "당근", "케첩", "간장", "설탕", "식용유"))
                        .steps(List.of("야채(양파, 피망, 당근)를 먹기 좋게 썬다.", "소시지는 칼집을 내거나 어슷썰기한다.", "팬에 기름을 두르고 야채를 먼저 볶는다.", "소시지를 넣고 함께 볶는다.", "케첩, 간장, 설탕을 섞은 양념을 넣고 버무리듯 볶는다."))
                        .substitutes(Map.of("소시지", "베이컨"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2019/04/03/ee34a886695dd69c8df2996d27671be51.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=소시지야채볶음")
                        .averageRating(4.4)
                        .build(),

                Recipe.builder()
                        .title("토마토파스타")
                        .difficulty(4)
                        .cookTime(30)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER))
                        .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                        .tasteTags(List.of(TasteType.SAVORY, TasteType.UMAMI, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.WHEAT, AllergyTag.MILK))
                        .ingredients(List.of("스파게티면", "올리브유", "마늘", "양파", "토마토소스", "파르메산 치즈", "소금"))
                        .steps(List.of("스파게티면을 소금물에 삶는다.", "팬에 올리브유를 두르고 마늘과 양파를 볶는다.", "토마토소스를 넣고 끓인다.", "삶은 면을 넣고 소스와 잘 섞는다.", "접시에 담고 파르메산 치즈를 뿌려 마무리한다."))
                        .substitutes(Map.of("토마토소스", "크림소스"))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2017/02/27/59ccdce3c03388a25dfd16e8f44e44491.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=토마토파스타")
                        .averageRating(4.5)
                        .build(),

                Recipe.builder().title("과일샐러드")
                        .difficulty(2)
                        .cookTime(10)
                        .mealTimes(List.of(MealTime.BREAKFAST, MealTime.LUNCH, MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SPRING, Season.SUMMER))
                        .tasteTags(List.of(TasteType.SWEET, TasteType.SOUR))
                        .allergyTags(List.of())
                        .ingredients(List.of("사과", "바나나", "오렌지", "포도", "요거트", "꿀", "레몬즙"))
                        .steps(List.of("모든 과일을 한 입 크기로 썬다.", "볼에 과일을 담고 요거트를 넣는다.", "꿀과 레몬즙을 넣고 잘 섞는다.", "차게 해서 접시에 담아낸다."))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2021/10/12/8fb47f58ed6c934d2e86dc9961e50a1b1.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=과일샐러드")
                        .averageRating(4.6)
                        .build(),

                Recipe.builder().title("해물파전")
                        .difficulty(4)
                        .cookTime(25)
                        .mealTimes(List.of(MealTime.LUNCH, MealTime.DINNER, MealTime.LATE_NIGHT))
                        .seasons(List.of(Season.SPRING, Season.FALL))
                        .tasteTags(List.of(TasteType.SAVORY, TasteType.UMAMI, TasteType.SALTY))
                        .allergyTags(List.of(AllergyTag.SHELLFISH, AllergyTag.WHEAT))
                        .ingredients(List.of("부침가루", "물", "오징어", "새우", "쪽파", "고추", "소금", "식용유"))
                        .steps(List.of(
                                "부침가루에 물을 넣고 반죽을 만든다.",
                                "손질한 오징어, 새우, 쪽파, 고추를 반죽에 섞는다.",
                                "팬에 기름을 넉넉히 두르고 반죽을 넓게 편다.",
                                "중불에서 앞뒤로 노릇하게 지져낸다.",
                                "기호에 따라 양념간장을 곁들여 낸다."
                        ))
                        .imageUrl("https://recipe1.ezmember.co.kr/cache/recipe/2017/11/04/66fdacb7533c367a4171c2ffb7de9fba1.jpg")
                        .videoUrl("https://www.youtube.com/results?search_query=해물파전")
                        .averageRating(4.5)
                        .build()
                );
        recipeRepository.saveAll(recipes);

        Recipe kimchi = recipeRepository.findByTitleContaining("김치볶음밥").get(0);
        Recipe toast = recipeRepository.findByTitleContaining("프렌치토스트").get(0);

        recipeService.addFavorite(1l, kimchi.getId());
        recipeService.addFavorite(1l, toast.getId());
    }
}
