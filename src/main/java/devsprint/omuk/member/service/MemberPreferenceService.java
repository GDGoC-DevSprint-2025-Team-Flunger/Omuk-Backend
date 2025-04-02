package devsprint.omuk.member.service;

import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.entity.TasteEntity;
import devsprint.omuk.member.entity.AllergyEntity;
import devsprint.omuk.member.entity.TimeEntity;
import devsprint.omuk.member.repository.TasteRepository;
import devsprint.omuk.member.repository.AllergyRepository;
import devsprint.omuk.member.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberPreferenceService {

    private final TasteRepository tasteRepository;
    private final AllergyRepository allergyRepository;
    private final TimeRepository timeRepository;

    @Transactional
    public void saveMemberPreference(MemberPreferenceRequest request) {
        Long memberId = request.getMemberId();

        // 취향 데이터 저장
        if (request.getTaste() != null) {
            request.getTaste().forEach(taste ->
                    tasteRepository.save(TasteEntity.builder()
                            .memberId(memberId)
                            .type(taste)
                            .build())
            );
        }

        // 알러지 데이터 저장
        if (request.getAllergy() != null) {
            request.getAllergy().forEach(allergy ->
                    allergyRepository.save(AllergyEntity.builder()
                            .memberId(memberId)
                            .type(allergy)
                            .build())
            );
        }

        // 선호 시간 데이터 저장
        if (request.getTime() != null) {
            request.getTime().forEach(time ->
                    timeRepository.save(TimeEntity.builder()
                            .memberId(memberId)
                            .type(time)
                            .build())
            );
        }
    }
}
