grammar ReqModelBachelorDriverLicense;

// Parser Rules
start: specification;

// Constitution of the Specification
specification: educationRequirement driverLicenseRequirement;

// What is your education level?
educationRequirement: EDUCATION_LEVEL COLLON BACHELOR;

// Do you have a driver license?
driverLicenseRequirement: DRIVER_LICENSE COLLON LICENSE;

EDUCATION_LEVEL: 'education-level';
DRIVER_LICENSE: 'driver-license';
BACHELOR: 'BACHELOR' | 'Bachelor' | 'bachelor';
LICENSE: 'yes' | 'YES' | 'Yes' ;

COLLON: ':';

WS : [ \t\r\n]+ -> skip ;
