package projet.spring.edraak.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.exceptions.SectionNotFoundException;
import projet.spring.edraak.model.Section;
import projet.spring.edraak.request.section.AddSectionRequest;
import projet.spring.edraak.request.section.SectionUpdateRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.section.ISectionService;

import java.util.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController
@RequestMapping("/api/v1/sections")
public class SectionController {
    private final ISectionService sectionService;

    public SectionController(ISectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllSections(){
        List<Section> sections = sectionService.getAllSections();
        return ResponseEntity.ok(new ApiResponse("Sections fetched successfully",sections));
    }
    @GetMapping("/{sectionId}")
    public ResponseEntity<ApiResponse> getSectionById(@PathVariable Long sectionId){
        try{
            Section section = sectionService.getSectionById(sectionId);
            return ResponseEntity.ok(new ApiResponse("Section fetched successfully",section));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addSection(@RequestBody AddSectionRequest request) {
        try {
            Section section = new Section(request.getName());
            Section newSection = sectionService.addSection(section);
            return ResponseEntity.ok(new ApiResponse("Section added successfully", newSection));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/{sectionId}/delete")
    public ResponseEntity<ApiResponse> deleteSection(@PathVariable Long sectionId){
        try{
            sectionService.deleteSection(sectionId);
            return ResponseEntity.ok(new ApiResponse("Section deleted successfully",null));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/{sectionId}/update")
    public ResponseEntity<ApiResponse> updateSection(@RequestBody SectionUpdateRequest request, @PathVariable Long sectionId) {
        try {
            Section section = new Section(request.getName());
            Section updatedSection = sectionService.updateSection(section, sectionId);
            return ResponseEntity.ok(new ApiResponse("Section updated successfully", updatedSection));
        } catch (SectionNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getSectionByName(@PathVariable String name) {
        try {
            Section section = sectionService.getSectionByName(name);
            return ResponseEntity.ok(new ApiResponse("Section fetched successfully", section));
        } catch (SectionNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
