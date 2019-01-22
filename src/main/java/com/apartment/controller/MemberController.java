package com.apartment.controller;

import com.apartment.entity.Member;
import com.apartment.service.MemberService;
import com.apartment.util.CustomErrorType;
import com.apartment.util.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class MemberController {

    @Autowired
    private MemberService memberService;
    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);


    @RequestMapping(value = "/member/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Member member) {
        logger.info("MemberController :: Creating Member : {}", member);
        JsonResponse jsonResponse = new JsonResponse();
        try {
            memberService.save(member);
        } catch (Exception e) {
            logger.error("MemberController :: Error While Creating Member :: ", e);
            jsonResponse.setMessage("Internal Server Error");
            jsonResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        jsonResponse.setMessage("Member Added Successfully");
        jsonResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> listAll() {
        logger.info("MemberController :: Getting All Members ");
        List<Member> members = memberService.findAll();
        if (members.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMember(@PathVariable("id") long id) {
        logger.info("Fetching Member with id {}", id);
        Member member = memberService.findById(id);
        if (member == null) {
            logger.error("Member with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Member with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMember(@PathVariable("id") long id, @RequestBody Member member) {
        logger.info("Updating Member with id {}", id);

        Member currentMember = memberService.findById(id);

        if (currentMember == null) {
            logger.error("Unable to update. Member with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Member with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentMember.setFirstname(member.getFirstname());
        currentMember.setLastname(member.getLastname());
        currentMember.setBlock(member.getBlock());
        currentMember.setFloor(member.getFloor());
        currentMember.setFlatNo(member.getFlatNo());
        currentMember.setIsdCode(member.getIsdCode());
        currentMember.setContactNumber(member.getContactNumber());
        currentMember.setIntercom(member.getIntercom());
        currentMember.setOwnerShip(member.getOwnerShip());
        currentMember.setEmail(member.getEmail());

        memberService.update(currentMember);

        return new ResponseEntity<Member>(currentMember, HttpStatus.OK);
    }
}
