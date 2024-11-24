:- initialization(main).

symptom(car1, 'does not start').
symptom(car1, 'battery dead').
symptom(car2, 'overheating').
symptom(car2, 'noise').
symptom(car3, 'does not start').
symptom(car3, 'battery dead').

problem(Car, 'battery replacement') :-
   symptom(Car, 'does not start'),
   symptom(Car, 'battery dead').

problem(Car, 'fuel system issue') :-
   symptom(Car, 'does not start'),
   symptom(Car, 'engine cranks').

problem(Car, 'cooling system issue') :-
   symptom(Car, 'overheating').

problem(Car, 'suspension issue') :-
   symptom(Car, 'noise').


main :-
    write('Diagnosing Issues:'), nl,
    
    (   problem(car1, Diagnosis1)
    ->  format("car1 is diagnosed with ~w.~n", [Diagnosis1])
    ;   write("No problem found for car1."), nl
    ),
    
    (   problem(car2, Diagnosis2)
    ->  format("car2 is diagnosed with ~w.~n", [Diagnosis2])
    ;   write("No problem found for car2."), nl
    ),
    
    (   problem(car3, Diagnosis3)
    ->  format("car3 is diagnosed with ~w.~n", [Diagnosis3])
    ;   write("No problem found for car3."), nl
    ).
