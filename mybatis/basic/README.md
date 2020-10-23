# Introduction

### StudentMapper#findAll()

Basic

### StudentMapper#findAllWithConditions

Use `<resultMap />` for mapping to object. Don't need to use `where 1=1` for appending additional conditions like `where 1=1 and ~ and ~`  if using `<where />`

### StudentMapper#findAllByNameQuery

for sharing duplicated part of query. and can use `if` or `foreach`.



