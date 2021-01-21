package com.fastcode.lkjh14.restcontrollers.core;

import com.fastcode.lkjh14.application.core.test4.ITest4AppService;
import com.fastcode.lkjh14.application.core.test4.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.commons.search.OffsetBasedPageRequest;
import com.fastcode.lkjh14.commons.search.SearchCriteria;
import com.fastcode.lkjh14.commons.search.SearchUtils;
import java.time.*;
import java.util.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test4")
@RequiredArgsConstructor
public class Test4Controller {

    @Qualifier("test4AppService")
    @NonNull
    protected final ITest4AppService _test4AppService;

    @NonNull
    protected final LoggingHelper logHelper;

    @NonNull
    protected final Environment env;

    @PreAuthorize("hasAnyAuthority('TEST4ENTITY_CREATE')")
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<CreateTest4Output> create(@RequestBody @Valid CreateTest4Input test4) {
        CreateTest4Output output = _test4AppService.create(test4);
        return new ResponseEntity(output, HttpStatus.OK);
    }

    // ------------ Delete test4 ------------
    @PreAuthorize("hasAnyAuthority('TEST4ENTITY_DELETE')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/json" })
    public void delete(@PathVariable String id) {
        FindTest4ByIdOutput output = _test4AppService.findById(Long.valueOf(id));
        Optional
            .ofNullable(output)
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("There does not exist a test4 with a id=%s", id))
            );

        _test4AppService.delete(Long.valueOf(id));
    }

    // ------------ Update test4 ------------
    @PreAuthorize("hasAnyAuthority('TEST4ENTITY_UPDATE')")
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.PUT,
        consumes = { "application/json" },
        produces = { "application/json" }
    )
    public ResponseEntity<UpdateTest4Output> update(
        @PathVariable String id,
        @RequestBody @Valid UpdateTest4Input test4
    ) {
        FindTest4ByIdOutput currentTest4 = _test4AppService.findById(Long.valueOf(id));
        Optional
            .ofNullable(currentTest4)
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("Unable to update. Test4 with id=%s not found.", id))
            );

        test4.setVersiono(currentTest4.getVersiono());
        UpdateTest4Output output = _test4AppService.update(Long.valueOf(id), test4);
        return new ResponseEntity(output, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('TEST4ENTITY_READ')")
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        consumes = { "application/json" },
        produces = { "application/json" }
    )
    public ResponseEntity<FindTest4ByIdOutput> findById(@PathVariable String id) {
        FindTest4ByIdOutput output = _test4AppService.findById(Long.valueOf(id));
        Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

        return new ResponseEntity(output, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('TEST4ENTITY_READ')")
    @RequestMapping(method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity find(
        @RequestParam(value = "search", required = false) String search,
        @RequestParam(value = "offset", required = false) String offset,
        @RequestParam(value = "limit", required = false) String limit,
        Sort sort
    )
        throws Exception {
        if (offset == null) {
            offset = env.getProperty("fastCode.offset.default");
        }
        if (limit == null) {
            limit = env.getProperty("fastCode.limit.default");
        }

        if (sort == null || sort.isEmpty()) {
            sort = Sort.by(Sort.Direction.ASC, "id");
        }

        Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
        SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);

        return ResponseEntity.ok(_test4AppService.find(searchCriteria, Pageable));
    }
}
