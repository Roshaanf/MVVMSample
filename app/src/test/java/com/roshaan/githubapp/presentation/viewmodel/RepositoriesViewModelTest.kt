package com.roshaan.githubapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.roshaan.githubapp.data.model.Repository
import com.roshaan.githubapp.data.repository.GithubRepository
import com.roshaan.githubapp.presentation.MainCoroutineRule
import com.roshaan.githubapp.presentation.view.adapter.RepositoriesAdapter
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RepositoriesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //    rule for coroutine main dispatcher
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var githubRepository: GithubRepository

    @Mock
    private lateinit var repositoryAdapter: RepositoriesAdapter

    private lateinit var viewModel: RepositoriesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = RepositoriesViewModel(
            githubRepository,
            repositoryAdapter
        )
    }

    @Test
    fun `update adapter items on fetchData response`() {
        runBlockingTest {
            stubFetchDataSuccess()

            viewModel.fetchData(false)

            verify(repositoryAdapter).addItems(getRepositoryList())
        }
    }

    @Test
    fun `fetchData fail, sets errorVisibility live data true`() {
        viewModel.fetchData(false)

        assertEquals(true, viewModel.errorVisibility.value)
    }

    @Test
    fun `fetchData success, sets errorVisibility live data false`() {
        runBlockingTest {
            stubFetchDataSuccess()

            viewModel.fetchData(false)

            assertEquals(false, viewModel.errorVisibility.value)
        }
    }

    @Test
    fun `fetchData fails, sets recyclerViewVisibility live data false`() {
        viewModel.fetchData(false)

        assertEquals(false, viewModel.reyclerViewVisibility.value)
    }

    @Test
    fun `fetchData success, sets recyclerViewVisibility live data false`() {
        runBlockingTest {
            stubFetchDataSuccess()

            viewModel.fetchData(false)

            assertEquals(true, viewModel.reyclerViewVisibility.value)
        }
    }

    @Test
    fun `set shimmerVisibility live data false on fetchData response`() {

        viewModel.fetchData(false)

        assertEquals(false, viewModel.shimmerVisibility.value)

    }

    @Test
    fun `set swipeLoaderVisibility live data false on fetchData response`() {

        viewModel.fetchData(false)

        assertEquals(false, viewModel.swipeLoaderVisibility.value)

    }

    private suspend fun stubFetchDataSuccess() {
        whenever(githubRepository.getRepositories(any())).thenReturn(
            getRepositoryList()
        )
    }

    private fun getRepositoryList() = listOf(
        Repository(
            "Owner", "Name", "Description",
            "avatar url", 2
        )
    )

}