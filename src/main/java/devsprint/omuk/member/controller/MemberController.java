package devsprint.omuk.member.controller;

import devsprint.omuk.member.repository.MemberRepository;
import devsprint.omuk.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController{
    @Autowired
    private MemberService memberService;


}
