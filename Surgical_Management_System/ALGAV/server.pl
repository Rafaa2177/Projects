:- use_module(library(http/thread_httpd)).
:- use_module(library(http/http_dispatch)).
:- use_module(library(http/http_json)).
:- use_module(library(http/http_open)).
:- use_module(library(http/json)).
:- use_module(library(http/http_parameters)).
:- use_module(library(lists)).
:- consult(algoritmo_algav).

url('https://localhost:5001/api').

http:location(api,'/api',[]).

:- http_handler(api(values), api_values ,[]).
:- http_handler(api(solution), api_get_solution, []).

% Função que converte a lista Schedule para JSON
schedule_json(Schedule, JSON) :-
    maplist(functor_to_json, Schedule, JSON).

% Função para converter cada tupla (Room, Date, FinalAgenda) em um dicionário JSON
% Aqui, transformamos as tuplas em listas para que o Prolog consiga serializar corretamente para JSON
functor_to_json((Room, Date, FinalAgenda), _{room: Room, date: Date, finalAgenda: FinalAgendaList}) :-
    maplist(tuple_to_list, FinalAgenda, FinalAgendaList).

% Função para converter tuplas (x, y, z) em listas [x, y, z] para que sejam serializáveis
tuple_to_list((X, Y, Z), [X, Y, Z]).


api_get_solution(Request) :-
    debug(http(request),
          'Received ~p', [Request]),
    http_read_json_dict(Request, Dict),
    (   _{room: Room, day: Day, heuristic: Heuristic} :< Dict
    ->
        
        
        atom_string(RoomAtom, Room),
        atom_string(HeuristicAtom, Heuristic),
        
        
        debug(http(request), 'Room: ~p, Day: ~p, Heuristic: ~p', [RoomAtom, Day, HeuristicAtom]),   
        generate_schedule(RoomAtom, Day,HeuristicAtom, Solution,ExecutionTime),
        schedule_json(Solution, JSON),
reply_json_dict(_{status: "ok", schedule:JSON, executionTime: ExecutionTime}, [status(200)])    ;
        reply_json_dict(_{status: "error", message: "Invalid parameters"})
).

init_server(Port) :-
    debug(http(request)),
    debug(server),
    http_server(http_dispatch, [port(Port)]).
   
:- init_server(4000).
  

