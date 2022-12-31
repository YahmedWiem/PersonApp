package demo.person.personapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import demo.person.personapp.data.PersonDataSource
import demo.person.personapp.presentation.PersonRepository

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Provides
    fun providesDataSource(@ApplicationContext context: Context) = PersonDataSource(context)

    @Provides
    fun providePersonRepository(dataSource: PersonDataSource) = PersonRepository(dataSource)
}