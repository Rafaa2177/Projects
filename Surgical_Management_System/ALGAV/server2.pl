:- use_module(library(http/thread_httpd)).
:- use_module(library(http/http_dispatch)).
:- use_module(library(http/http_json)).
:- use_module(library(http/http_open)).
:- use_module(library(http/json)).
:- use_module(library(http/http_parameters)).
:- use_module(library(lists)).
:- consult(better_schedule).

url('https://localhost:5001/api').

http:location(api,'/api',[]).

:- http_handler(api(values), api_values ,[]).
:- http_handler(api(solution), api_get_solution, []).

% Função que converte a lista Schedule para JSON
schedule_json(Schedule, JSON) :-
    maplist(functor_to_json, Schedule, JSON).

% Função para converter cada tupla (Room, Date, FinalAgenda) em um dicionário JSON
% Aqui, transformamos as tuplas em listas para que o Prolog consiga serializar corretamente para JSON
functor_to_json((Doctor, FinalAgenda), _{doctor: Doctor, finalAgenda: FinalAgendaList}) :-
    maplist(tuple_to_list, FinalAgenda, FinalAgendaList).

% Função para converter tuplas (x, y, z) em listas [x, y, z] para que sejam serializáveis
tuple_to_list((X, Y, Z), [X, Y, Z]).


api_get_solution(Request) :-
    debug(http(request),
          'Received ~p', [Request]),
    http_read_json_dict(Request, Dict),
    (   _{room: Room, day: Day} :< Dict
    ->
        
        
        atom_string(RoomAtom, Room),
        
        
        
        obtain_better_sol(RoomAtom, Day,AgOpRoomBetter,LAgDoctorsBetter,TFinOp,T),
        maplist(tuple_to_list, AgOpRoomBetter, AgOpRoomBetterList),
        schedule_json(LAgDoctorsBetter, JSON),
        write('Solution: '), write(AgOpRoomBetter), nl,  
    reply_json_dict(_{status: "success", solution:AgOpRoomBetterList,lAgDoctorsBetter: JSON ,finalTime: TFinOp, executionTime: T}, [status(200)])
;
    reply_json_dict(_{status: "error", message: "Invalid parameters"})
).

init_server(Port) :-
    debug(http(request)),
    debug(server),
    http_server(http_dispatch, [port(Port)]).
   
:- init_server(4000).
  

