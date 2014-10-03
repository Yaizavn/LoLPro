package com.lol.lolpro.app.bbdd;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lol.lolpro.app.R;

/**
 * Created by sergioiglesias on 20/09/14.
 */
public class dFragment extends DialogFragment {
    DescargarBBDD mTask;
    ProgressBar mProgressBar;
    TextView mText;

    public void setTask(DescargarBBDD task)
    {
        mTask = task;

        // Tell the AsyncTask to call updateProgress() and taskFinished() on this fragment.
        mTask.setFragment(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Retain this instance so it isn't destroyed when MainActivity and
        // MainFragment change configuration.
        setRetainInstance(true);

        // Start the task! You could move this outside this activity if you want.
        if (mTask != null)
            mTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dfragment, container);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        mText = (TextView)view.findViewById(R.id.message);
        mProgressBar.setMax(100);

        getDialog().setTitle(getString(R.string.descargando_titulo));

        // If you're doing a long task, you probably don't want people to cancel
        // it just by tapping the screen!
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }

    // This is to work around what is apparently a bug. If you don't have it
    // here the dialog will be dismissed on rotation, so tell it not to dismiss.
    @Override
    public void onDestroyView()
    {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    // Also when we are dismissed we need to cancel the task.
    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        // If true, the thread is interrupted immediately, which may do bad things.
        // If false, it guarantees a result is never returned (onPostExecute() isn't called)
        // but you have to repeatedly call isCancelled() in your doInBackground()
        // function to check if it should exit. For some tasks that might not be feasible.
        if (mTask != null) {
            mTask.cancel(true);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // This is a little hacky, but we will see if the task has finished while we weren't
        // in this activity, and then we can dismiss ourselves.
        if (mTask == null)
            dismiss();
    }

    // This is called by the AsyncTask.
    public void updateProgress(int percent, String message)
    {
        mProgressBar.setProgress(percent);
        mText.setText(message);
    }

    // This is also called by the AsyncTask.
    public void taskFinished()
    {
        // Make sure we check if it is resumed because we will crash if trying to dismiss the dialog
        // after the user has switched to another app.
        if (isResumed())
            dismiss();

        // If we aren't resumed, setting the task to null will allow us to dimiss ourselves in
        // onResume().
        mTask = null;
    }
}
