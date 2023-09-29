package com.company.LibraryProject.service;

import com.company.LibraryProject.dto.ErrorDto;
import com.company.LibraryProject.dto.PublisherDto;
import com.company.LibraryProject.dto.ResponseDto;
import com.company.LibraryProject.model.Publisher;
import com.company.LibraryProject.repository.PublisherRepository;
import com.company.LibraryProject.service.mapper.PublisherMapper;
import com.company.LibraryProject.service.validation.PublisherValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublisherService {
    private final PublisherMapper publisherMapper;
    private final PublisherValidate publisherValidate;
    private final PublisherRepository publisherRepository;


    public ResponseDto<PublisherDto> createPublisher(PublisherDto publisherDto) {
        List<ErrorDto> errors = this.publisherValidate.validate(publisherDto);
        if (!errors.isEmpty()) {
            log.warn("Validate error!");
            return ResponseDto.<PublisherDto>builder()
                    .message("Validate error!")
                    .code(-2)
                    .data(publisherDto)
                    .errors(errors)
                    .build();
        }
        try {
            Publisher publisher = publisherMapper.toEntity(publisherDto);
            publisher.setCreatedAt(LocalDateTime.now());
            publisherRepository.save(publisher);
            return ResponseDto.<PublisherDto>builder()
                    .success(true)
                    .code(0)
                    .message("This publisher successful created")
                    .data(publisherMapper.toDtoNotBookId(publisher))
                    .build();

        } catch (Exception e) {
            log.warn(String.format("Publisher saving error %s", e.getMessage()));
            return ResponseDto.<PublisherDto>builder()
                    .message("Publisher %S id saving error" + publisherDto.getPublisherId())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<PublisherDto> getPublisher(Integer id) {
        Optional<Publisher> optional = publisherRepository.findByPublisherIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            log.warn(String.format("This is publisher %d id is not found!", id));
            return ResponseDto.<PublisherDto>builder()
                    .message(String.format("This is publisher %d id is not found!", id))
                    .code(-1)
                    .build();
        }
        log.info("OK");
        return ResponseDto.<PublisherDto>builder()
                .data(publisherMapper.toDto(optional.get()))
                .success(true)
                .code(0)
                .message("Ok")
                .build();
    }


    public ResponseDto<PublisherDto> updatePublisher(PublisherDto dto, Integer id) {
        try {
            Optional<Publisher> optional = publisherRepository.findByPublisherIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                log.warn(String.format("This is publisher %d id is not found!", id));
                return ResponseDto.<PublisherDto>builder()
                        .message(String.format("This is publisher %d id is not found!", id))
                        .code(-1)
                        .build();
            }
            List<ErrorDto> errors = this.publisherValidate.validate(dto);
            if (!errors.isEmpty()) {
                log.warn("Validate error!");
                return ResponseDto.<PublisherDto>builder()
                        .message("Validate error!")
                        .code(-2)
                        .data(dto)
                        .errors(errors)
                        .build();
            }
            Publisher publisher = optional.get();
            publisher.setUpdatedAt(LocalDateTime.now());
            publisherMapper.update(publisher,dto);
            publisherRepository.save(publisher);
            log.info("Successful updated!");
            return ResponseDto.<PublisherDto>builder()
                    .data(publisherMapper.toDtoNotBookId(publisher))
                    .success(true)
                    .code(0)
                    .message("Successful updated!")
                    .build();

        } catch (Exception e) {
            log.warn(String.format("Publisher saving error %s", e.getMessage()));
            return ResponseDto.<PublisherDto>builder()
                    .message("Publisher saving error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<PublisherDto> deletePublisher(Integer id) {
        try {
            Optional<Publisher> optional = publisherRepository.findByPublisherIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                log.warn(String.format("This is publisher %d id is not found!", id));
                return ResponseDto.<PublisherDto>builder()
                        .message(String.format("This is publisher %d id is not found!", id))
                        .code(-1)
                        .build();
            }
            Publisher publisher = optional.get();
            publisher.setDeletedAt(LocalDateTime.now());
            publisherRepository.save(publisher);
            log.info("Successful deleted!");
            return ResponseDto.<PublisherDto>builder()
                    .data(publisherMapper.toDtoNotBookId(publisher))
                    .success(true)
                    .code(0)
                    .message("Successful deleted!")
                    .build();

        } catch (Exception e) {
            log.warn(String.format("Publisher saving error %s", e.getMessage()));
            return ResponseDto.<PublisherDto>builder()
                    .message("Publisher saving error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<List<PublisherDto>> getAllPublisher() {
        log.info("OK");
        return ResponseDto.<List<PublisherDto>>builder()
                .message("OK")
                .code(0)
                .success(true)
                .data(publisherRepository.findAll().stream().map(publisherMapper::toDto).toList())
                .build();
    }
}
