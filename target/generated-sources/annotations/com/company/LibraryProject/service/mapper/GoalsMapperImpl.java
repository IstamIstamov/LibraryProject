package com.company.LibraryProject.service.mapper;

import com.company.LibraryProject.dto.GoalsDto;
import com.company.LibraryProject.model.Goals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-29T23:00:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class GoalsMapperImpl implements GoalsMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public Goals toEntity(GoalsDto goalsDto) {
        if ( goalsDto == null ) {
            return null;
        }

        Goals goals = new Goals();

        goals.setGoalsId( goalsDto.getGoalsId() );
        goals.setLanguage( goalsDto.getLanguage() );
        goals.setDescription( goalsDto.getDescription() );
        goals.setBookId( goalsDto.getBookId() );

        return goals;
    }

    @Override
    public GoalsDto toDto(Goals goals) {
        if ( goals == null ) {
            return null;
        }

        GoalsDto goalsDto = new GoalsDto();

        if ( goals.getCreatedAt() != null ) {
            goalsDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( goals.getCreatedAt() ) );
        }
        if ( goals.getUpdatedAt() != null ) {
            goalsDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( goals.getUpdatedAt() ) );
        }
        if ( goals.getDeletedAt() != null ) {
            goalsDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( goals.getDeletedAt() ) );
        }
        goalsDto.setGoalsId( goals.getGoalsId() );
        goalsDto.setLanguage( goals.getLanguage() );
        goalsDto.setDescription( goals.getDescription() );
        goalsDto.setBookId( goals.getBookId() );

        return goalsDto;
    }

    @Override
    public GoalsDto toDtoNotBookId(Goals goals) {
        if ( goals == null ) {
            return null;
        }

        GoalsDto goalsDto = new GoalsDto();

        if ( goals.getCreatedAt() != null ) {
            goalsDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( goals.getCreatedAt() ) );
        }
        if ( goals.getUpdatedAt() != null ) {
            goalsDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( goals.getUpdatedAt() ) );
        }
        if ( goals.getDeletedAt() != null ) {
            goalsDto.setDeletedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( goals.getDeletedAt() ) );
        }
        goalsDto.setGoalsId( goals.getGoalsId() );
        goalsDto.setLanguage( goals.getLanguage() );
        goalsDto.setDescription( goals.getDescription() );

        return goalsDto;
    }

    @Override
    public void update(Goals goals, GoalsDto goalsDto) {
        if ( goalsDto == null ) {
            return;
        }

        if ( goalsDto.getGoalsId() != null ) {
            goals.setGoalsId( goalsDto.getGoalsId() );
        }
        if ( goalsDto.getLanguage() != null ) {
            goals.setLanguage( goalsDto.getLanguage() );
        }
        if ( goalsDto.getDescription() != null ) {
            goals.setDescription( goalsDto.getDescription() );
        }
        if ( goalsDto.getBookId() != null ) {
            goals.setBookId( goalsDto.getBookId() );
        }
        if ( goalsDto.getCreatedAt() != null ) {
            goals.setCreatedAt( LocalDateTime.parse( goalsDto.getCreatedAt() ) );
        }
        if ( goalsDto.getUpdatedAt() != null ) {
            goals.setUpdatedAt( LocalDateTime.parse( goalsDto.getUpdatedAt() ) );
        }
        if ( goalsDto.getDeletedAt() != null ) {
            goals.setDeletedAt( LocalDateTime.parse( goalsDto.getDeletedAt() ) );
        }
    }
}
