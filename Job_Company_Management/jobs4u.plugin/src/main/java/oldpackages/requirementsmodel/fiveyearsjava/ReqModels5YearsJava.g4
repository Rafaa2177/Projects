grammar ReqModels5YearsJava;

// Parser Rules
start: specification;

// Constitution of the Specification
specification: experienceRequirement languageRequirement;

// How many years of experience do you have?
experienceRequirement: YEARS_EXPERIENCE COLLON AT_LEAST_5_YEARS;

// Which programming languages do you know?
languageRequirement: PROGRAMMING_LANGUAGES COLLON JAVA;

YEARS_EXPERIENCE: 'years-experience';
PROGRAMMING_LANGUAGES: 'programming-languages';
JAVA: 'Java' | 'java' | 'JAVA';
AT_LEAST_5_YEARS: [5-9] | [1-9][0-9]+;

COLLON: ':';

WS : [ \t\r\n]+ -> skip ;


