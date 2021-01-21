package com.fastcode.lkjh14.restcontrollers.core;

import com.fastcode.lkjh14.application.core.test6.ITest6AppService;
import com.fastcode.lkjh14.application.core.test6.dto.*;
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
@RequestMapping("/test6")
@RequiredArgsConstructor
public class Test6Controller {

    @Qualifier("test6AppService")
    @NonNull
    protected final ITest6AppService _test6AppService;

    @NonNull
    protected final LoggingHelper logHelper;

    @NonNull
    protected final Environment env;

    @PreAuthorize("hasAnyAuthority('TEST6ENTITY_CREATE')")
    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<CreateTest6Output> create(@RequestBody @Valid CreateTest6Input test6) {
        CreateTest6Output output = _test6AppService.create(test6);
        return new ResponseEntity(output, HttpStatus.OK);
    }

    // ------------ Delete test6 ------------
    @PreAuthorize("hasAnyAuthority('TEST6ENTITY_DELETE')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/json" })
    public void delete(@PathVariable String id) {
        FindTest6ByIdOutput output = _test6AppService.findById(Integer.valueOf(id));
        Optional
            .ofNullable(output)
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("There does not exist a test6 with a id=%s", id))
            );

        _test6AppService.delete(Integer.valueOf(id));
    }

    // ------------ Update test6 ------------
    @PreAuthorize("hasAnyAuthority('TEST6ENTITY_UPDATE')")
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.PUT,
        consumes = { "application/json" },
        produces = { "application/json" }
    )
    public ResponseEntity<UpdateTest6Output> update(
        @PathVariable String id,
        @RequestBody @Valid UpdateTest6Input test6
    ) {
        FindTest6ByIdOutput currentTest6 = _test6AppService.findById(Integer.valueOf(id));
        Optional
            .ofNullable(currentTest6)
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("Unable to update. Test6 with id=%s not found.", id))
            );

        test6.setVersiono(currentTest6.getVersiono());
        UpdateTest6Output output = _test6AppService.update(Integer.valueOf(id), test6);
        return new ResponseEntity(output, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('TEST6ENTITY_READ')")
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        consumes = { "application/json" },
        produces = { "application/json" }
    )
    public ResponseEntity<FindTest6ByIdOutput> findById(@PathVariable String id) {
        FindTest6ByIdOutput output = _test6AppService.findById(Integer.valueOf(id));
        Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

        return new ResponseEntity(output, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('TEST6ENTITY_READ')")
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

        return ResponseEntity.ok(_test6AppService.find(searchCriteria, Pageable));
    }
}
