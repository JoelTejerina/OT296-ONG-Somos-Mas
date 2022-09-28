package com.alkemy.ong.service.implement;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesDTOPublic;
import com.alkemy.ong.entity.Slides;
import com.alkemy.ong.mapper.SlidesMapper;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.SlidesService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlidesServiceImpl implements SlidesService {
    @Autowired
    private SlidesRepository slidesRepository;
    @Autowired
    private SlidesMapper slidesMapper;

    @Override
    public List<SlidesDTOPublic> getSlidesDTO() {
        List<Slides> entities = slidesRepository.findAll();
        return slidesMapper.slidesEntityList2DTO(entities);
    }

    @Override
    public SlidesDTO getSlideDTO(String id) throws NotFoundException {
        if (!slidesRepository.existsById(id)) {
            throw new NotFoundException("Slide not found");
        }
        Slides entity = slidesRepository.findById(id).orElse(null);
        assert entity != null;
        return slidesMapper.SlidesEntity2DTO(entity);
    }
}
