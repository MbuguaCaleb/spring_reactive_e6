```aidl

Important to note is that methods in mono can be used in a 
similar way in Flux.

Both are publishers.

Beware of taking elements outside of the pipeline to process them iteratively.

That is one mistake which most developers do/make.

(This is what we call/refer to as using blocking methods)


Even when working with a Stream we should stick to the pipeline
as much as possible.

When we take an element out of the pipeline,it forces us
to block the thread 

We can no longer work with it anymore in a reactive approach
which is against the practices of using reactive.

Do avoid as much as possible anything that is blocking.

Whenever a method returns something else rather than a 
publisher from the docs, it means it is a blocking method


Documentatation Link,

https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html

It is like making your application non-reactive

doOnNext--->Very clear it is going to execute after each value 
of the Flux.

doOnComplete-->When i want to wait till after the last element
of the flux then execute.

doOnCancel-->remember that a subscription has request and cancel
method.


```

```aidl

When working with rest endpoints,springboot takes care of our subscribers
    
Back-pressure

The subscriber requests the number of events it wants to have.

```

**Important Methods in the Reactive Pipeline**

```aidl

1.Log Event.

It can either be used before or after the predicate and helps
us se how the stream events were called.

return f1.filter(s->s.length() % 2 == 0)
         .log();
         
         
2.distinct()


It helps eliminate all the duplicates in the Stream.


3.map

transforms data from one Type to Another


4.flatMap

It is used when i want to transform the data in my Stream

Not necesarrily from one type to another but rather the Output.


5.doOnNext()

It is used when i want to do something on each element.

doOnNext() methods return publishers and they are therefore not blocking

Avoid blocking your code when working on reactive applications.

We should always use approaches that return a publisher.



6.collect method

Good Approach

Returns a List from a flIX

Collect is a method that helps me return a List in a Non-Blocking Way.

@GetMapping(value = "/demo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Mono<List<String>> demo(){

Flux<String> f1 = Flux.just("AAA","AAA","BBB","BBB","CCC","DDD","EEE","FF","AAA");

return  f1.collect(Collectors.toList());


Wrong Implementation

 @GetMapping(value = "/demo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public List<String> demo(){

        Flux<String> f1 = Flux.just("AAA","AAA","BBB","BBB","CCC","DDD","EEE","FF","AAA");
        List<String> list = new ArrayList<>();


         f1.doOnNext(s-> list.add(s));

        
         return list;
    }
    
   
7.concatWith

It is used to join one flux to another

   Flux<String> f1 = Flux.just("AAA", "AAA", "BBB", "BBB", "CCC", "DDD", "EEE", "FF", "AAA");
        Flux<String> f2 = Flux.just("caleb", "mercy", "ruth", "sharon", "brian", "boniface");
        
return f1.concatWith(f2);

8.thenMany

When concatenating two fluxes, thenMany only returns the second Flux

It is useful when i want to return the second Flux and not the first

   Flux<String> f1 = Flux.just("AAA", "AAA", "BBB", "BBB", "CCC", "DDD", "EEE", "FF", "AAA");
        Flux<String> f2 = Flux.just("caleb", "mercy", "ruth", "sharon", "brian", "boniface");
        
return f1.thenMany(f2);


9.zipWith

When i want to mix the values of a Flux with each other,i use the zipWith Method

If one flux is shorter the values will be mixed up to that point

  Flux<String> f1 = Flux.just("AAA", "AAA", "BBB", "BBB", "CCC", "DDD", "EEE", "FF", "AAA");
  Flux<String> f2 = Flux.just("mercy", "ruth", "sharon","brian");
               
 return f1 .zipWith(f2,(x,y) -> x + "=" +y );
```


**N/B**
```aidl
It is very important to not the types of what i return
in Java.

```

**Notes By**

```aidl

Mbugua Caleb

```