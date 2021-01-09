package com.roshaan.githubapp.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatchcer

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatchcer
