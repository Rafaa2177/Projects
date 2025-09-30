:- dynamic availability/3.
:- dynamic agenda_staff/3.
:- dynamic agenda_staff1/3.
:- dynamic surgery_id/2.
:- dynamic agenda_operation_room/3.
:- dynamic agenda_operation_room1/3.
:- dynamic assignment_surgery/2.
:- dynamic timetable/3.
:- dynamic staff/4.
:-dynamic better_sol/5.


:- use_module(library(listing)).

agenda_operation_room(or1,20241028,[]).

agenda_staff(d001,20241028,[(720,790,m01),(1080,1140,c01)]).
agenda_staff(d002,20241028,[(850,900,m02),(901,960,m02),(1380,1440,c02)]).
agenda_staff(d003,20241028,[(720,790,m01),(910,980,m02)]).
agenda_staff(d004,20241028,[(850,900,m02),(940,980,c04)]).
agenda_staff(a001, 20241028, []).
agenda_staff(a002, 20241028, []).
agenda_staff(n001, 20241028, []).

timetable(d001,20241028,(480,1200)).
timetable(d002,20241028,(500,1440)).
timetable(d003,20241028,(520,1320)).
timetable(d004,20241028,(620,1020)).
timetable(a001, 20241028, (480, 1200)).
timetable(a002, 20241028, (480, 1440)).
timetable(n001, 20241028, (480, 1200)).


surgery(so2,45,60,45).
surgery(so3,45,90,45).
surgery(so4,45,75,45).

surgery_id(so100001,so2).
surgery_id(so100002,so3).
surgery_id(so100003,so4).
surgery_id(so100004,so2).
surgery_id(so100005,so4).
surgery_id(so100006,so2).
surgery_id(so100007,so3).
surgery_id(so100008,so2).
surgery_id(so100009,so2).
surgery_id(so100010,so2).
%surgery_id(so100011,so4).
%surgery_id(so100012,so2).
%surgery_id(so100013,so2).

assignment_surgery(so100001,d001).
assignment_surgery(so100002,d002).
assignment_surgery(so100003,d003).
assignment_surgery(so100004,d001).
assignment_surgery(so100004,d002).
assignment_surgery(so100005,d002).
assignment_surgery(so100005,d003).
assignment_surgery(so100006,d001).
assignment_surgery(so100007,d003).
assignment_surgery(so100008,d004).
%assignment_surgery(so100008,d003).
assignment_surgery(so100009,d002).
%assignment_surgery(so100009,d004).
assignment_surgery(so100010,d003).
assignment_surgery(so100011,d001).
assignment_surgery(so100012,d001).
assignment_surgery(so100013,d004).
assignment_staff(so100001, a001).
assignment_staff(so100002, a002).
assignment_staff(so100003, a001).
assignment_staff(so100004, a002).
