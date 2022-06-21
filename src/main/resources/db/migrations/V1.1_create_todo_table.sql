create table Todo{
    id serial primary key,
    title varchar(20) not null,
    description varchar(100),
    isCompleted boolean,
    created_at timestamp null,
    updated_at timestamp null
};