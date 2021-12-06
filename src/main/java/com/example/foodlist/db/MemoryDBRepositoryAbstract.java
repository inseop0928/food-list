package com.example.foodlist.db;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDBRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDBRepositoryInterface<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(s->s.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {

        Optional<T> optionalEntitiy =  db.stream().filter(s->s.getIndex()==entity.getIndex()).findFirst();

        if(optionalEntitiy.isEmpty()){
            entity.setIndex(++index);
            db.add(entity);
            return entity;
        }else{
            int preIndex = optionalEntitiy.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        Optional<T> optionalEntitiy =  db.stream().filter(s->s.getIndex()==index).findFirst();

        if(optionalEntitiy.isPresent()){//데이터가 있는경우
            db.remove(optionalEntitiy.get());
        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
