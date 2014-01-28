package org.fenixedu.sdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fenixedu.sdk.api.CurricularResourcesAsync;
import org.fenixedu.sdk.api.EvaluationResourcesAsync;
import org.fenixedu.sdk.api.PaymentResourcesAsync;
import org.fenixedu.sdk.api.PersonalResourcesAsync;
import org.fenixedu.sdk.api.PublicResourcesAsync;
import org.fenixedu.sdk.api.ScheduleResourcesAsync;

import pt.ist.fenixedu.sdk.CalendarFormat;
import pt.ist.fenixedu.sdk.EnrolAction;
import pt.ist.fenixedu.sdk.FenixEduClient;
import pt.ist.fenixedu.sdk.FenixEduClientBaseImpl;
import pt.ist.fenixedu.sdk.FenixEduClientImpl;
import pt.ist.fenixedu.sdk.api.FenixEduEndpoint;
import pt.ist.fenixedu.sdk.auth.Authorization;
import pt.ist.fenixedu.sdk.config.FenixEduClientConfigurationManager.Configuration;
import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class AsyncFenixEduClientImpl extends FenixEduClientBaseImpl implements AsyncFenixEduClient {

    private static final long serialVersionUID = -6837035370386828780L;

    private final FenixEduClient fenixEduClient;
    private final List<FenixEduClientListener> listeners;

    private static transient Dispatcher dispatcher;

    public AsyncFenixEduClientImpl(Configuration config, Authorization auth) throws IOException {
        super(config, auth);
        this.fenixEduClient = new FenixEduClientImpl(config, auth);
        this.listeners = new ArrayList<FenixEduClientListener>();
    }

    @Override
    public void addListener(FenixEduClientListener listener) {
        listeners.add(listener);
    }

    private Dispatcher getDispatcher() {
        if (null == AsyncFenixEduClientImpl.dispatcher) {
            synchronized (AsyncFenixEduClientImpl.class) {
                if (null == AsyncFenixEduClientImpl.dispatcher) {
                    AsyncFenixEduClientImpl.dispatcher = new DispatcherImpl(config);
                }
            }
        }
        return AsyncFenixEduClientImpl.dispatcher;
    }

    abstract class AsyncTask implements Runnable {
        List<FenixEduClientListener> listeners;
        FenixEduEndpoint endpoint;

        AsyncTask(FenixEduEndpoint endpoint, List<FenixEduClientListener> listeners) {
            this.endpoint = endpoint;
            this.listeners = listeners;
        }

        abstract void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException;

        @Override
        public void run() {
            try {
                invoke(listeners);
            } catch (FenixEduClientException te) {
                if (listeners != null) {
                    for (FenixEduClientListener listener : listeners) {
                        try {
                            listener.onException(te, endpoint);
                        } catch (Exception ignore) {
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getAbout() {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.ABOUT, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject about = fenixEduClient.getAbout();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotAbout(about);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getAcademicTerms() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.ACADEMIC_TERMS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject academicTerms = fenixEduClient.getAcademicTerms();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotAcademicTerms(academicTerms);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getCourse(final String courseId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.COURSE, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject course = fenixEduClient.getCourse(courseId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotCourse(course);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getCourseEvaluations(final String courseId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.COURSE_EVALUATIONS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray courseEvaluations = fenixEduClient.getCourseEvaluations(courseId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotCourseEvaluations(courseEvaluations);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getCourseGroups(final String courseId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.COURSE_GROUPS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray courseGroups = fenixEduClient.getCourseGroups(courseId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotCourseGroups(courseGroups);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getCourseSchedule(final String courseId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.COURSE_SCHEDULE, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject courseSchedule = fenixEduClient.getCourseSchedule(courseId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotCourseSchedule(courseSchedule);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getCourseStudents(final String courseId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.COURSE_STUDENTS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject courseStudents = fenixEduClient.getCourseStudents(courseId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotCourseStudents(courseStudents);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDegrees() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.DEGREES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray degrees = fenixEduClient.getDegrees();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotDegrees(degrees);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDegrees(final String academicTerm) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.DEGREES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray degrees = fenixEduClient.getDegrees(academicTerm);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotDegrees(degrees);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDegree(final String degreeId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.DEGREE, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject degree = fenixEduClient.getDegree(degreeId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotDegree(degree);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDegree(final String degreeId, final String academicTerm) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.DEGREE, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject degree = fenixEduClient.getDegree(degreeId, academicTerm);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotDegree(degree);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDegreeCourses(final String degreeId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.DEGREE_COURSES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray degreeCourses = fenixEduClient.getDegreeCourses(degreeId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotDegreeCourses(degreeCourses);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDegreeCourses(final String degreeId, final String academicTerm) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.DEGREE_COURSES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray degreeCourses = fenixEduClient.getDegreeCourses(degreeId, academicTerm);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotDegreeCourses(degreeCourses);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getSpaces() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.SPACES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray spaces = fenixEduClient.getSpaces();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotSpaces(spaces);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getSpace(final String spaceId, final String day) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.SPACE, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject space = fenixEduClient.getSpace(spaceId, day);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotSpace(space);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getSpaceBlueprint(final String spaceId) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.SPACE, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                byte[] blueprint = fenixEduClient.getSpaceBlueprint(spaceId);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotSpaceBlueprint(blueprint);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPerson() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject person = fenixEduClient.getPerson();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPerson(person);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonEvaluations() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_EVALUATIONS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray personEvaluations = fenixEduClient.getPersonEvaluations();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonEvaluations(personEvaluations);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void enrollPersonInEvaluation(final String evaluationId, final EnrolAction action) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_EVALUATION, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray evaluationEnrolments = fenixEduClient.enrollPersonInEvaluation(evaluationId, action);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonEvaluationEnrollment(evaluationEnrolments);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonCourses() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_COURSES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject personCourses = fenixEduClient.getPersonCourses();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonCourses(personCourses);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonCourses(final String academicTerm) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_COURSES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject personCourses = fenixEduClient.getPersonCourses(academicTerm);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonCourses(personCourses);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonCurriculum() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_CURRICULUM, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonArray personCurriculum = fenixEduClient.getPersonCurriculum();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonCurriculum(personCurriculum);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonPayments() throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_PAYMENTS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject personPayments = fenixEduClient.getPersonPayments();
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonPayments(personPayments);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonCalendarClasses(final CalendarFormat format) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_CALENDAR_CLASSES, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject personCalendarClasses = fenixEduClient.getPersonCalendarClasses(format);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonCalendarClasses(personCalendarClasses);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public void getPersonCalendarEvaluations(final CalendarFormat format) throws FenixEduClientException {
        getDispatcher().invokeLater(new AsyncTask(FenixEduEndpoint.PERSON_CALENDAR_EVALUATIONS, listeners) {
            @Override
            public void invoke(List<FenixEduClientListener> listeners) throws FenixEduClientException {
                JsonObject personCalendarClasses = fenixEduClient.getPersonCalendarEvaluations(format);
                for (FenixEduClientListener listener : listeners) {
                    try {
                        listener.gotPersonCalendarClasses(personCalendarClasses);
                    } catch (Exception ignore) {
                    }
                }
            }
        });
    }

    @Override
    public PublicResourcesAsync publicScope() {
        return this;
    }

    @Override
    public PersonalResourcesAsync personalScope() {
        return this;
    }

    @Override
    public ScheduleResourcesAsync scheduleScope() {
        return this;
    }

    @Override
    public CurricularResourcesAsync curricularScope() {
        return this;
    }

    @Override
    public EvaluationResourcesAsync evaluationScope() {
        return this;
    }

    @Override
    public PaymentResourcesAsync paymentScope() {
        return this;
    }

}
