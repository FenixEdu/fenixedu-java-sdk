package pt.ist.fenixedu.sdk.beans.publico;

import java.util.ArrayList;
import java.util.List;

public class FenixSpace {

    public static class Campus extends FenixSpace {
    	
    	public Campus() {
    	}
    	
        public Campus(String id, String name) {
            super(id, name, "CAMPUS");
        }

        public Campus(String id, String name, List<FenixSpace> containedSpaces, FenixSpace parentSpace) {
            super(id, name, "CAMPUS", containedSpaces, parentSpace);
        }
    }

    public static class Building extends FenixSpace {
        public Building(String id, String name) {
            super(id, name, "BUILDING");
        }

        public Building(String id, String name, List<FenixSpace> containedSpaces, FenixSpace parentSpace) {
            super(id, name, "BUILDING", containedSpaces, parentSpace);
        }

    }

    public static class Floor extends FenixSpace {
    	
    	public Floor() {
    	}
    	
        public Floor(String id, String name) {
            super(id, name, "FLOOR");
        }

        public Floor(String id, String name, List<FenixSpace> containedSpaces, FenixSpace parentSpace) {
            super(id, name, "FLOOR", containedSpaces, parentSpace);
        }

    }

    public static class Room extends FenixSpace {
    	
    	public static class RoomEvent {

            public static class LessonEvent extends RoomEvent {
                private String info;
                private WrittenEvaluationEvent.ExecutionCourse course;
                
                public LessonEvent() {
                }

                public LessonEvent(String start, String end, String weekday, String info,
                        WrittenEvaluationEvent.ExecutionCourse course) {
                    super(start, end, weekday);
                    this.info = info;
                    this.course = course;
                }

				public String getInfo() {
					return info;
				}

				public void setInfo(String info) {
					this.info = info;
				}

				public WrittenEvaluationEvent.ExecutionCourse getCourse() {
					return course;
				}

				public void setCourse(WrittenEvaluationEvent.ExecutionCourse course) {
					this.course = course;
				}
            }

            public static abstract class WrittenEvaluationEvent extends RoomEvent {
            	
            	public WrittenEvaluationEvent() {
            	}

                public static class ExecutionCourse {
                    private String acronym;
                    private String name;
                    private String id;

                    public ExecutionCourse(String acronym, String name, String id) {
                        super();
                        this.acronym = acronym;
                        this.name = name;
                        this.id = id;
                    }

					public String getAcronym() {
						return acronym;
					}

					public void setAcronym(String acronym) {
						this.acronym = acronym;
					}

					public String getName() {
						return name;
					}

					public void setName(String name) {
						this.name = name;
					}

					public String getId() {
						return id;
					}

					public void setId(String id) {
						this.id = id;
					}
                }

                public static class TestEvent extends WrittenEvaluationEvent {
                    private String description;

                    public TestEvent() {
                    }
                    
                    public TestEvent(String start, String end, String weekday, List<ExecutionCourse> courses, String description) {
                        super(start, end, weekday, courses);
                        this.description = description;
                    }

					public String getDescription() {
						return description;
					}

					public void setDescription(String description) {
						this.description = description;
					}
                }

                public static class ExamEvent extends WrittenEvaluationEvent {
                    private Integer season;

                    public ExamEvent() {
                    }
                    
                    public ExamEvent(String start, String end, String weekday, List<ExecutionCourse> courses, Integer season) {
                        super(start, end, weekday, courses);
                        this.season = season;
                    }

					public Integer getSeason() {
						return season;
					}

					public void setSeason(Integer season) {
						this.season = season;
					}
                    
                    
                }

                private List<WrittenEvaluationEvent.ExecutionCourse> courses;

                public WrittenEvaluationEvent(String start, String end, String weekday, List<ExecutionCourse> courses) {
                    super(start, end, weekday);
                    this.courses = courses;
                }

				public List<WrittenEvaluationEvent.ExecutionCourse> getCourses() {
					return courses;
				}

				public void setCourses(List<WrittenEvaluationEvent.ExecutionCourse> courses) {
					this.courses = courses;
				}
            }

            public static class GenericEvent extends RoomEvent {
                private String description;
                private String title;

                public GenericEvent() {
                }
                
                public GenericEvent(String start, String end, String weekday, String description, String title) {
                    super(start, end, weekday);
                    this.description = description;
                    this.title = title;
                }

				public String getDescription() {
					return description;
				}

				public void setDescription(String description) {
					this.description = description;
				}

				public String getTitle() {
					return title;
				}

				public void setTitle(String title) {
					this.title = title;
				}
            }

            private String start;
            private String end;
            private String weekday;
            
            public RoomEvent() {
            }

            public RoomEvent(String start, String end, String weekday) {
                super();
                this.start = start;
                this.end = end;
                this.weekday = weekday;
            }

			public String getStart() {
				return start;
			}

			public void setStart(String start) {
				this.start = start;
			}

			public String getEnd() {
				return end;
			}

			public void setEnd(String end) {
				this.end = end;
			}

			public String getWeekday() {
				return weekday;
			}

			public void setWeekday(String weekday) {
				this.weekday = weekday;
			}
        }

        private String description;
        private Integer normalCapacity;
        private Integer examCapacity;

        private List<RoomEvent> events;

        public Room(String id, String name) {
            super(id, name, "ROOM");
        }

        public Room(String id, String name, FenixSpace parentSpace, String description, Integer normalCapacity,
                Integer examCapacity, List<RoomEvent> events) {
            super(id, name, "ROOM", null, parentSpace);
            this.description = description;
            this.normalCapacity = normalCapacity;
            this.examCapacity = examCapacity;
            if (events == null || events.isEmpty()) {
                events = new ArrayList<RoomEvent>();
            }
            this.events = events;
        }

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getNormalCapacity() {
			return normalCapacity;
		}

		public void setNormalCapacity(Integer normalCapacity) {
			this.normalCapacity = normalCapacity;
		}

		public Integer getExamCapacity() {
			return examCapacity;
		}

		public void setExamCapacity(Integer examCapacity) {
			this.examCapacity = examCapacity;
		}

		public List<RoomEvent> getEvents() {
			return events;
		}

		public void setEvents(List<RoomEvent> events) {
			this.events = events;
		}
    }

    private String id;
    private String name;
    private String type;
    private List<FenixSpace> containedSpaces;
    private FenixSpace parentSpace;
    
    public FenixSpace() {
    }

    public FenixSpace(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentSpace = null;
        this.containedSpaces = null;
    }

    public FenixSpace(String id, String name, String type, List<FenixSpace> containedSpaces, FenixSpace parentSpace) {
        this(id, name, type);
        this.containedSpaces = containedSpaces;
        this.parentSpace = parentSpace;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FenixSpace> getContainedSpaces() {
		return containedSpaces;
	}

	public void setContainedSpaces(List<FenixSpace> containedSpaces) {
		this.containedSpaces = containedSpaces;
	}

	public FenixSpace getParentSpace() {
		return parentSpace;
	}

	public void setParentSpace(FenixSpace parentSpace) {
		this.parentSpace = parentSpace;
	}
    
    
}