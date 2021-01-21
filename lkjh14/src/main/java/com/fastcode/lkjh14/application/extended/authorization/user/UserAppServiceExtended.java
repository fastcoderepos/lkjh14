package com.fastcode.lkjh14.application.extended.authorization.user;

import com.fastcode.lkjh14.addons.reporting.domain.dashboardversion.*;
import com.fastcode.lkjh14.addons.reporting.domain.dashboardversionreport.*;
import com.fastcode.lkjh14.addons.reporting.domain.reportversion.*;
import com.fastcode.lkjh14.application.core.authorization.user.UserAppService;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.domain.core.authorization.userpreference.IUserpreferenceRepository;
import com.fastcode.lkjh14.domain.extended.authorization.user.IUserRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("userAppServiceExtended")
public class UserAppServiceExtended extends UserAppService implements IUserAppServiceExtended {

    public UserAppServiceExtended(
        IDashboardversionRepository dashboardversionRepository,
        IReportversionRepository reportversionRepository,
        IDashboardversionreportRepository reportDashboardRepository,
        IUserRepositoryExtended userRepositoryExtended,
        IUserpreferenceRepository userpreferenceRepository,
        IUserMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(
            dashboardversionRepository,
            reportversionRepository,
            reportDashboardRepository,
            userRepositoryExtended,
            userpreferenceRepository,
            mapper,
            logHelper
        );
    }
    //Add your custom code here

}
