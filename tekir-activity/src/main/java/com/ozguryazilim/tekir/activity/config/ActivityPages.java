package com.ozguryazilim.tekir.activity.config;

import com.ozguryazilim.tekir.activity.ActivityFeature;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.MainNavigationSection;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 * Activity View Configs
 *
 * @author Hakan Uygun
 */
@ApplicationScoped
@Folder(name = "./activities")
public interface ActivityPages extends Pages {

    @View
    @SecuredPage
    class ActivityWidget implements ActivityPages {
    }

    @View
    @SecuredPage
    @PageTitle("module.caption.ActivityBrowse")
    @Navigation(label = "module.caption.ActivityBrowse",
            feature = ActivityFeature.class,
            section = MainNavigationSection.class)
    class ActivityBrowse implements ActivityPages {
    }

    @View
    @SecuredPage
    @PageTitle("module.caption.Activity")
    class Activity implements ActivityPages {
    }

    @View
    @SecuredPage
    @PageTitle("module.caption.Activity")
    class ActivityView implements ActivityPages {
    }

    @View
    @SecuredPage
    @PageTitle("module.caption.Activity")
    class ActivityMasterView implements ActivityPages {
    }

    @View
    @SecuredPage
    class ActivityReminderCommand implements ActivityPages {
    }

    @View
    @SecuredPage
    class EMailFetchCommand implements ActivityPages {
    }

    @View
    @SecuredPage
    class ActivityQuickPanel implements ActivityPages {
    }

    @View
    @SecuredPage
    class EMailImportOptionPane implements ActivityPages {
    }

    @SecuredPage
    interface Phone extends ActivityPages {

        @View
        @SecuredPage
        class PhoneActivityEditor implements Phone {
        }

        @View
        @SecuredPage
        class PhoneActivityFragment implements Phone {
        }
    }

    @SecuredPage
    interface EMail extends ActivityPages {

        @View
        @SecuredPage
        class EMailActivityEditor implements EMail {
        }

        @View
        @SecuredPage
        class EMailActivityFragment implements EMail {
        }
    }

    @SecuredPage
    interface Meeting extends ActivityPages {

        @View
        @SecuredPage
        class MeetingActivityEditor implements Meeting {
        }

        @View
        @SecuredPage
        class MeetingActivityFragment implements Meeting {
        }
    }

    @SecuredPage
    interface Task extends ActivityPages {

        @View
        @SecuredPage
        class TaskActivityEditor implements Task {
        }

        @View
        @SecuredPage
        class TaskActivityFragment implements Task {
        }
    }

    @SecuredPage
    interface Comment extends ActivityPages {

        @View
        @SecuredPage
        class CommentActivityEditor implements Comment {
        }

        @View
        @SecuredPage
        class CommentActivityFragment implements Comment {
        }
    }
}
