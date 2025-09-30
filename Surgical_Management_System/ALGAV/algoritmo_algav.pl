:- consult('base_de_conhecimento.pl').

% Função principal para gerar o agendamento
generate_schedule(Room, Date, Heuristic, Schedule, ExecutionTime) :-
    get_time(StartTime), % Captura o tempo de início
    (Heuristic == 'greedy' -> greedy_schedule(Room, Date, Schedule);
     Heuristic == 'rule-based' -> rule_based_schedule(Room, Date, Schedule)),
    get_time(EndTime), % Captura o tempo de término
    ExecutionTime is EndTime - StartTime. % Calcula o tempo de execução
    %write('Execution Time: '), write(ExecutionTime), write(' seconds'), nl. % Exibe o tempo de execução

% Implementação da heurística greedy
greedy_schedule(Room, Date, Schedule) :-
    retractall(agenda_staff1(_,_,_)),
    retractall(agenda_operation_room1(_,_,_)),
    retractall(availability(_,_,_)),
    findall(_,(agenda_staff(D,Date,Agenda),assertz(agenda_staff1(D,Date,Agenda))),_),
    agenda_operation_room(Room,Date,Agenda),assert(agenda_operation_room1(Room,Date,Agenda)),
    findall(_,(agenda_staff1(D,Date,L),free_agenda0(L,LFA),adapt_timetable(D,Date,LFA,LFA2),assertz(availability(D,Date,LFA2))),_),
    findall(OpCode,surgery_id(OpCode,_),LOpCode),
    write('LOpCode: '), write(LOpCode), nl, 
    availability_all_surgeries(LOpCode,Room,Date),
    agenda_operation_room1(Room,Date,FinalAgenda),
    Schedule = [(Room,Date,FinalAgenda)].
    %write('Schedule: '), write(Schedule), nl. 

% Implementação da heurística rule-based
rule_based_schedule(Room, Date, Schedule) :-
    retractall(agenda_staff1(_,_,_)),
    retractall(agenda_operation_room1(_,_,_)),
    retractall(availability(_,_,_)),
    findall(_,(agenda_staff(D,Date,Agenda),assertz(agenda_staff1(D,Date,Agenda))),_),
    agenda_operation_room(Room,Date,Agenda),assert(agenda_operation_room1(Room,Date,Agenda)),
    findall(_,(agenda_staff1(D,Date,L),free_agenda0(L,LFA),adapt_timetable(D,Date,LFA,LFA2),assertz(availability(D,Date,LFA2))),_),
    findall(OpCode,surgery_id(OpCode,_),LOpCode),
    sort_surgeries_by_duration(LOpCode, SortedLOpCode), 
    %write('Sorted LOpCode: '), write(SortedLOpCode), nl, 
    availability_all_surgeries(SortedLOpCode,Room,Date),
    agenda_operation_room1(Room,Date,FinalAgenda),
    Schedule = [(Room,Date,FinalAgenda)].
    %write('Schedule: '), write(Schedule), nl. 
% Ordena as cirurgias por duração (do maior para o menor)
sort_surgeries_by_duration(LOpCode, SortedLOpCode) :-
    map_list_to_pairs(surgery_duration, LOpCode, Pairs),
    keysort(Pairs, SortedPairs),
    pairs_values(SortedPairs, SortedLOpCode).

surgery_duration(OpCode, Duration) :-
    surgery_id(OpCode, OpType),
    surgery(OpType, _, Duration, _).

% Funções auxiliares
free_agenda0([],[(0,1440)]).
free_agenda0([(0,Tfin,_)|LT],LT1):-!,free_agenda1([(0,Tfin,_)|LT],LT1).
free_agenda0([(Tin,Tfin,_)|LT],[(0,T1)|LT1]):- T1 is Tin-1,
    free_agenda1([(Tin,Tfin,_)|LT],LT1).

free_agenda1([(_,Tfin,_)],[(T1,1440)]):-Tfin\==1440,!,T1 is Tfin+1.
free_agenda1([(_,_,_)],[]).
free_agenda1([(_,T,_),(T1,Tfin2,_)|LT],LT1):-Tx is T+1,T1==Tx,!,
    free_agenda1([(T1,Tfin2,_)|LT],LT1).
free_agenda1([(_,Tfin1,_),(Tin2,Tfin2,_)|LT],[(T1,T2)|LT1]):-T1 is Tfin1+1,T2 is Tin2-1,
    free_agenda1([(Tin2,Tfin2,_)|LT],LT1).

adapt_timetable(D,Date,LFA,LFA2):-timetable(D,Date,(InTime,FinTime)),treatin(InTime,LFA,LFA1),treatfin(FinTime,LFA1,LFA2).

treatin(InTime,[(In,Fin)|LFA],[(In,Fin)|LFA]):-InTime=<In,!.
treatin(InTime,[(_,Fin)|LFA],LFA1):-InTime>Fin,!,treatin(InTime,LFA,LFA1).
treatin(InTime,[(_,Fin)|LFA],[(InTime,Fin)|LFA]).
treatin(_,[],[]).

treatfin(FinTime,[(In,Fin)|LFA],[(In,Fin)|LFA1]):-FinTime>=Fin,!,treatfin(FinTime,LFA,LFA1).
treatfin(FinTime,[(In,_)|_],[]):-FinTime=<In,!.
treatfin(FinTime,[(In,_)|_],[(In,FinTime)]).
treatfin(_,[],[]).

intersect_all_agendas([Name],Date,LA):-!,availability(Name,Date,LA).
intersect_all_agendas([Name|LNames],Date,LI):-
    availability(Name,Date,LA),
    intersect_all_agendas(LNames,Date,LI1),
    intersect_2_agendas(LA,LI1,LI).

intersect_2_agendas([],_,[]).
intersect_2_agendas([D|LD],LA,LIT):-	intersect_availability(D,LA,LI,LA1),
                    intersect_2_agendas(LD,LA1,LID),
                    append(LI,LID,LIT).

intersect_availability((_,_),[],[],[]).

intersect_availability((_,Fim),[(Ini1,Fim1)|LD],[],[(Ini1,Fim1)|LD]):-
        Fim<Ini1,!.

intersect_availability((Ini,Fim),[(_,Fim1)|LD],LI,LA):-
        Ini>Fim1,!,
        intersect_availability((Ini,Fim),LD,LI,LA).

intersect_availability((Ini,Fim),[(Ini1,Fim1)|LD],[(Imax,Fmin)],[(Fim,Fim1)|LD]):-
        Fim1>Fim,!,
        min_max(Ini,Ini1,_,Imax),
        min_max(Fim,Fim1,Fmin,_).

intersect_availability((Ini,Fim),[(Ini1,Fim1)|LD],[(Imax,Fmin)|LI],LA):-
        Fim>=Fim1,!,
        min_max(Ini,Ini1,_,Imax),
        min_max(Fim,Fim1,Fmin,_),
        intersect_availability((Fim1,Fim),LD,LI,LA).

min_max(I,I1,I,I1):- I<I1,!.
min_max(I,I1,I1,I).

availability_all_surgeries([],_,_).
availability_all_surgeries([OpCode|LOpCode],Room,Day):-
    surgery_id(OpCode,OpType),
    surgery(OpType,_,TSurgery,_),
    availability_operation(OpCode,Room,Day,LPossibilities,LDoctors),
    %write('Scheduling '), write(OpCode), write('...'), nl,
    (LPossibilities \= [] -> (
        schedule_first_interval(TSurgery,LPossibilities,(TinS,TfinS)),
        write('Scheduled: '), write(OpCode), write(' ('), write(TinS), write('-'), write(TfinS), write(')'), nl,
        retract(agenda_operation_room1(Room,Day,Agenda)),
        insert_agenda((TinS,TfinS,OpCode),Agenda,Agenda1),
        assertz(agenda_operation_room1(Room,Day,Agenda1)),
        insert_agenda_doctors((TinS,TfinS,OpCode),Day,LDoctors),
        insert_agenda_staff((TinS, TfinS, OpCode), Day, LStaff)
    )) ,%write('No valid interval for '), write(OpCode), nl),
    availability_all_surgeries(LOpCode,Room,Day).

availability_operation(OpCode,Room,Day,LPossibilities,LDoctors):-
    surgery_id(OpCode,OpType),
    surgery(OpType,_,TSurgery,_),
    findall(Doctor,assignment_surgery(OpCode,Doctor),LDoctors),
    findall(Staff, assignment_staff(OpCode, Staff), LStaff),
    append(LDoctors, LStaff, LAllStaff),
    intersect_all_agendas(LAllStaff,Day,LA),
    agenda_operation_room1(Room,Day,LAgenda),
    free_agenda0(LAgenda,LFAgRoom),
    intersect_2_agendas(LA,LFAgRoom,LIntAgAllStaffRoom),
    %write('Intersections for '), write(OpCode), write(': '), write(LIntAgAllStaffRoom), nl,
    remove_unf_intervals(TSurgery,LIntAgAllStaffRoom,LPossibilities).
    %write('Filtered intervals for '), write(OpCode), write(': '), write(LPossibilities), nl.

remove_unf_intervals(_,[],[]).
remove_unf_intervals(TSurgery,[(Tin,Tfin)|LA],[(Tin,Tfin)|LA1]):-DT is Tfin-Tin+1,TSurgery=<DT,!,
    remove_unf_intervals(TSurgery,LA,LA1).
remove_unf_intervals(TSurgery,[_|LA],LA1):- remove_unf_intervals(TSurgery,LA,LA1).

schedule_first_interval(TSurgery,Intervals,(Tin,Tfin)) :-
    sort(Intervals, SortedIntervals), % Ordena os intervalos por início
    member((Tin,TfinCandidate), SortedIntervals),
    Tfin is Tin + TSurgery - 1, % Calcula o fim do intervalo
    Tfin =< TfinCandidate, !. % Garante que o intervalo é válido.

insert_agenda((TinS,TfinS,OpCode),[],[(TinS,TfinS,OpCode)]).
insert_agenda((TinS,TfinS,OpCode),[(Tin,Tfin,OpCode1)|LA],[(TinS,TfinS,OpCode),(Tin,Tfin,OpCode1)|LA]):-TfinS<Tin,!.
insert_agenda((TinS,TfinS,OpCode),[(Tin,Tfin,OpCode1)|LA],[(Tin,Tfin,OpCode1)|LA1]):-insert_agenda((TinS,TfinS,OpCode),LA,LA1).

insert_agenda_doctors(_,_,[]).
insert_agenda_doctors((TinS,TfinS,OpCode),Day,[Doctor|LDoctors]):-
    retract(agenda_staff1(Doctor,Day,Agenda)),
    insert_agenda((TinS,TfinS,OpCode),Agenda,Agenda1),
    assert(agenda_staff1(Doctor,Day,Agenda1)),
    insert_agenda_doctors((TinS,TfinS,OpCode),Day,LDoctors).

insert_agenda_staff(_, _, []).
insert_agenda_staff((TinS, TfinS, OpCode), Day, [Staff|LStaff]) :-
    retract(agenda_staff1(Staff, Day, Agenda)),
    insert_agenda((TinS, TfinS, OpCode), Agenda, Agenda1),
    assert(agenda_staff1(Staff, Day, Agenda1)),
    insert_agenda_staff((TinS, TfinS, OpCode), Day, LStaff).